package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitHandler
    @RabbitListener(queues = "fanout.Q1")
    public void processA(String message) {
        System.out.println("fanout Receiver Q1: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.Q2")
    public void processB(String message) {
        System.out.println("fanout Receiver Q2: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.Q3")
    public void processC(String message) {
        System.out.println("fanout Receiver Q3: " + message);
    }
}