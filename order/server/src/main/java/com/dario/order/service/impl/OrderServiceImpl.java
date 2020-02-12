package com.dario.order.service.impl;

import com.dario.order.client.GoodsClient;
import com.dario.order.common.DecreaseStockInput;
import com.dario.order.common.GoodsInfoOutput;
import com.dario.order.dataobject.OrderDetail;
import com.dario.order.dataobject.OrderMaster;
import com.dario.order.dto.OrderDTO;
import com.dario.order.exception.OrderException;
import com.dario.order.repository.OrderDetailRepository;
import com.dario.order.repository.OrderMasterRepository;
import com.dario.order.service.OrderService;
import com.dario.order.enums.OrderStatusEnum;
import com.dario.order.enums.PayStatusEnum;
import com.dario.order.enums.ResultEnum;
import com.dario.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private GoodsClient goodsClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        try {
            String orderId = KeyUtil.getuniqueKey();
            //查询商品信息（调用商品服务）
            List<String> goodsIdList = orderDTO.getOrderDetailList().stream()
                    .map(OrderDetail::getGoodsId).collect(Collectors.toList());
            List<GoodsInfoOutput> goodsInfoOutputList = goodsClient.listForOrder(goodsIdList);
            //计算总价
            BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                for (GoodsInfoOutput goodsInfoOutput : goodsInfoOutputList) {
                    if (goodsInfoOutput.getGoodsId().equals(orderDetail.getGoodsId())) {
                        //计算总价
                        orderAmout = goodsInfoOutput.getGoodsPrice()
                                .multiply(new BigDecimal(orderDetail.getGoodsQuantity()))
                                .add(orderAmout);
                        BeanUtils.copyProperties(goodsInfoOutput, orderDetail);
                        orderDetail.setOrderId(orderId);
                        orderDetail.setDetailId(KeyUtil.getuniqueKey());
                        //订单详情入库
                        orderDetailRepository.save(orderDetail);
                    }
                }
            }
            //lambda表达式中要求orderAmout为最终变量
//        orderDTO.getOrderDetailList().stream().forEach(orderDetail -> {
//            goodsInfoOutputList.stream().forEach(goodsInfoOutput -> {
//                if (goodsInfoOutput.getGoodsId().equals(orderDetail.getGoodsId())){
//                  //计算总价
//                   orderAmout=goodsInfoOutput.getGoodsPrice().multiply(new BigDecimal(orderDetail.getGoodsQuantity())).add(orderAmout);
//                   BeanUtils.copyProperties(goodsInfoOutput,orderDetail);
//                   orderDetail.setOrderId(orderId);
//                   orderDetail.setDetailId(KeyUtil.getuniqueKey());
//                }
//            });
//        });
            //扣库存
            List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                    .map(orderDetail -> new DecreaseStockInput(orderDetail.getGoodsId(), orderDetail.getGoodsQuantity()))
                    .collect(Collectors.toList());
            goodsClient.decreaseStock(decreaseStockInputList);
            //存订单
            OrderMaster orderMaster = new OrderMaster();
            orderDTO.setOrderId(orderId);
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMaster.setOrderAmount(orderAmout);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
            Date date=new Date();
            orderMaster.setCreateTime(date);
            orderMaster.setUpdateTime(date);
            OrderMaster resutl = orderMasterRepository.save(orderMaster);
            return orderDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) throws Exception {
            //查询你订单
            Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
            if (!orderMasterOptional.isPresent())
                throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
            //判断订单状态
            OrderMaster orderMaster = orderMasterOptional.get();
            if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode())
                throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
            //修改订单为完结
            orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
            //更新到数据库中
            orderMasterRepository.save(orderMaster);

            //构造OrderDTO 查询订单详情（一个订单id 对应多个订单详情）
            List<OrderDetail> orderDetailList = findOrderDetailListByOrderId(orderId);
            if (CollectionUtils.isEmpty(orderDetailList)) {
                throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
            }
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTO.setOrderDetailList(orderDetailList);
            return orderDTO;
    }

    /**
     * 查询订单详情列表
     */
    @Override
    @Transactional
    public List<OrderDetail> findOrderDetailListByOrderId(String orderId) throws Exception {
        return orderDetailRepository.findAllByOrderId(orderId);
    }
}
