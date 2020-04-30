package com.dario.order.controller;

import com.dario.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*
 * 发送端
 * */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;


    @GetMapping("/sendMessage")
    public String process() {
        streamClient.output().send(MessageBuilder.withPayload("bingo--->now output method" + new Date()).build());
        return "ok";
    }
}
