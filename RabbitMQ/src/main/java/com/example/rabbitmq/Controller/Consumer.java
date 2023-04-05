package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue"))
    public void processMessage(String content) {
        System.out.println("消费者1=====" + content);
    }
}