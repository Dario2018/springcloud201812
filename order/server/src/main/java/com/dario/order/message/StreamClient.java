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

    String INPUT="sendToInput";

    String INPUT2="sendToInput2";

    @Input(value =StreamClient.INPUT)
    SubscribableChannel input();

    @Output
    MessageChannel output();

    @Input(value =StreamClient.INPUT2)
    SubscribableChannel input2();

    @Output
    MessageChannel output2();
}
