package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ExchangeConsumer {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("direct.Q1"))
    public void processQ1(String message) {
        System.out.println("direct Receiver Q1: " + message);
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("direct.Q2"))
    public void processQ2(String message) {
        System.out.println("direct Receiver Q2: " + message);
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("direct.Q3"))
    public void processQ3(String message) {
        System.out.println("direct Receiver Q3: " + message);
    }
}
