package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/topic/{routingKey}")
    public void produce(@PathVariable String routingKey) {
        String context = "topic msg weiz";
        System.out.println("topic Sender,routingKey: " + routingKey + ",context:" + context);
        this.rabbitTemplate.convertAndSend("topicExchange", routingKey, context);
    }
}