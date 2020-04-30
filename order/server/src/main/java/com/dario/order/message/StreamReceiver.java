package com.dario.order.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * spring-cloud-stream 实现消息中间件的事件驱动
 *
 * @author Dario
 * @Date 2018-12-27
 */
@Component
@EnableBinding({StreamClient.class})
@Slf4j
public class StreamReceiver {


    @StreamListener(StreamClient.INPUT)//监听input通道
    @SendTo(StreamClient.INPUT2)//放回的结果发送到output通道
    public String process(Object message) {
        log.info("process StreamReceiver:{}", message);
        System.out.println(message);
        return "received.";
    }


    /**
     * 接收从proces1发过来的信息，输入
     * 这个相当于消费者
     */
    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message) {
        log.info("process2 receivered message={}", message);
        System.out.println(message);
    }

}
