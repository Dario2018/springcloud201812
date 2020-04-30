package com.dario.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/*
 *
 *
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
