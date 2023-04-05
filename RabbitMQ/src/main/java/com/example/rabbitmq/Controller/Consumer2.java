package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue"))
    public void processMessage(String content) {
        System.out.println("消费者2=====" + content);
    }
}