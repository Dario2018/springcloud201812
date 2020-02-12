package com.dario.goods.service.impl;

import com.dario.goods.common.DecreaseStockInput;
import com.dario.goods.common.GoodsInfoOutput;
import com.dario.goods.entity.Goods;
import com.dario.goods.enums.GoodsStatusEnum;
import com.dario.goods.exception.GoodslException;
import com.dario.goods.repository.GoodsRepository;
import com.dario.goods.service.GoodsService;
import com.google.gson.Gson;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<Goods> queryAllByGoodsStatus() throws Exception {
        return goodsRepository.queryAllByGoodsStatus(GoodsStatusEnum.UP.getCode());
    }

    @Override
    public List<GoodsInfoOutput> listForOrder(List<String> goodsIdList) throws Exception {
//         List<GoodsInfoOutput> goodsInfoOutputList =nul;
//         List<Goods> goodsList=null;
//        Optional.ofNullable(goodsList=goodsRepository.findGoodsByGoodsIdIn(goodsIdList)).orElse(null);
//        goodsList.stream().forEach(goods -> {
//            GoodsInfoOutput goodsInfoOutput=new GoodsInfoOutput();
//            BeanUtils.copyProperties(goods,goodsInfoOutput);
//            goodsInfoOutputList.add(goodsInfoOutput);
//        });
        List<GoodsInfoOutput> goodsInfoOutputList = goodsRepository.findGoodsByGoodsIdIn(goodsIdList).stream()
                .map(goods -> {
                    GoodsInfoOutput goodsInfoOutput = new GoodsInfoOutput();
                    BeanUtils.copyProperties(goods, goodsInfoOutput);
                    return goodsInfoOutput;
                }).collect(Collectors.toList());
        return goodsInfoOutputList;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<Goods> goodsList = decreaseStockProcess(decreaseStockInputList);
        List<GoodsInfoOutput> goodsInfoOutputList = goodsList.stream().map(goods -> {
            GoodsInfoOutput goodsInfoOutput = new GoodsInfoOutput();
            BeanUtils.copyProperties(goods, goodsInfoOutput);
            return goodsInfoOutput;
        }).collect(Collectors.toList());
        //后续补充发送mq消息：这里的goodsInfo需要和接收方的命名相同,这里接收方法在order/message/GoodsReceiver
        amqpTemplate.convertAndSend("goodsInfo", new Gson().toJson(goodsInfoOutputList));
    }

    @Transactional
    public List<Goods> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputs) {
        List<Goods> goodsList = new ArrayList<Goods>();
        decreaseStockInputs.stream().forEach(decreaseStockInput -> {
            Optional<Goods> goodsOptional = goodsRepository.findById(decreaseStockInput.getGoodsId());
            //判断商品是否存在
            if (!goodsOptional.isPresent()) {
                throw new GoodslException(GoodsStatusEnum.GOODS_NOT_EXIST);
            }

            Goods goods = goodsOptional.get();
            //判断库存是否足够
            Integer result = goods.getGoodsStock() - decreaseStockInput.getGoodsQuantity();
            if (result < 0) {
                throw new GoodslException(GoodsStatusEnum.GOODS_AMOUT_NOT_ENOUGH);
            }
            //更新数据库
            goods.setGoodsStock(result);
            goods.setUpdateTime(new Date());
            goodsRepository.save(goods);

            goodsList.add(goods);

        });
        return goodsList;
    }
}
