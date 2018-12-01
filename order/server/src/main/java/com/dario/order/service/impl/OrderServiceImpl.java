package com.dario.order.service.impl;

import com.dario.order.dataobject.OrderDetail;
import com.dario.order.dataobject.OrderMaster;
import com.dario.order.dto.OrderDTO;
import com.dario.order.repository.OrderDetailRepository;
import com.dario.order.repository.OrderMasterRepository;
import com.dario.order.service.OrderService;
import com.dario.order.unums.OrderStatusEnum;
import com.dario.order.unums.PayStatusEnum;
import com.dario.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

//    @Autowired
//    private GoodsClient goodsClient;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {



        String orderId = KeyUtil.getuniqueKey();
        //查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        //计算总结
        //扣库存
        //存订单
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(1000));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
         OrderMaster resutl=orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
