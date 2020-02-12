package com.dario.order.message;

import com.dario.order.common.ConstantUtil;
import com.dario.order.common.GoodsInfoOutput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 * 消费信息（扣完库存所发送的信息）
 * */
@Component
@Slf4j
public class GoodsReceiver {



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("goodsInfo"))
    public void process(String goodsListJson){
        System.out.println("接收扣完返回的消息 》》》》》》》》》》》》");
        //goodsJson >>> GoodsInfoOutput
         Gson gson=new Gson();
         List<GoodsInfoOutput> goodsInfoOutputList=gson.fromJson(goodsListJson, new TypeToken<List<GoodsInfoOutput>>(){}.getType());
        log.info("从队列【{}】接收到消息：{}","goodsInfo",goodsInfoOutputList);

        //存储到redis
        goodsInfoOutputList.forEach( goodsInfoOutput -> {
        stringRedisTemplate.opsForValue().set(String.format(ConstantUtil.GOODS_STOCK_TEMPLATE,goodsInfoOutput.getGoodsId()),
                String.valueOf(goodsInfoOutput.getGoodsStock()));
        });

    }


}
