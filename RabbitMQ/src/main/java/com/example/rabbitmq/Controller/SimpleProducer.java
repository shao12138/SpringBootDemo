package com.example.rabbitmq.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SimpleProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/simple")
    public void produce() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String message = new Date() + "Beijing---" + i;
            System.out.println("生产者生产消息=====" + message);
            rabbitTemplate.convertAndSend("rabbitmq_queue",message);
            Thread.sleep(500);
        }
    }
}
