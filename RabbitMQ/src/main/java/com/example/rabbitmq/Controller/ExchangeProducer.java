package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/exchange/{routingKey}")
    public void produce(@PathVariable String routingKey) {
        String context = "direct msg weiz";
        System.out.println("Direct Sender,routingKey: " + routingKey + ",context:" + context);
        this.rabbitTemplate.convertAndSend("directExchange", routingKey, context);
    }
}
