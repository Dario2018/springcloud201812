package com.dario.order.message;

import com.dario.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","信息：now="+new Date());
    }


    @Test
    public void sendComputer(){
        amqpTemplate.convertAndSend("myOrder","computer","水果信息：now="+new Date());
    }

    @Test
    public void sendFruit(){
        amqpTemplate.convertAndSend("myOrder","fruit","电脑信息：now="+new Date());
    }
}