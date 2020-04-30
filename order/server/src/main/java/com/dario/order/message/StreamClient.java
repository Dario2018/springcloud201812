package com.dario.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/*
 *StpringCloud Stream 封装与消息队列的交互
 * 1.StreamClient.class 为 stream 组件
 * 2.在 test中AmqpTemplate 直接与中间件交互，那操作也可以
 * 3.接口存在两种类型的通道
 * 各个通道名字不能一样
 * 如果没有指定value，则
 * 方法名为通道的名字
 * 需要在配置yml 配置通道
 * 的绑定（bingder）
 * */
public interface StreamClient {

    String INPUT = "sendToInput"; // 消息来向通道名字
    String OUTPUT = "output";  // 消息发向的通道名字

    String INPUT2 = "sendToInput2";

    @Input(value = StreamClient.INPUT)
    SubscribableChannel input();

    @Output(value = StreamClient.OUTPUT)
    MessageChannel output();

    @Input(value = StreamClient.INPUT2)
    SubscribableChannel input2();

}
