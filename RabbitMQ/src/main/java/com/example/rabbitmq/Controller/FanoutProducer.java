package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/fanout")
    public void produce() {
        String context = "fanout msg Beijing";
        System.out.println("Fanout Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
    }
}