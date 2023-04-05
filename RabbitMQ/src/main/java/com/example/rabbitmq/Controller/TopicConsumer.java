package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitHandler
    @RabbitListener(queues = "topic.color")
    public void processA(String message) {
        System.out.println("topic.color Receiver: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.color.red")
    public void processB(String message) {
        System.out.println("topic.color.red Receiver: " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.msg.feedback")
    public void processC(String message) {
        System.out.println("topic.msg.feedback Receiver: " + message);
    }
}