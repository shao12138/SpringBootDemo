package com.example.rabbitmq.Service;

import org.springframework.amqp.core.*;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public static final String EXCHANGE_NAME = "boot.fanout";
    public static final String[] QUEUE_NAME = {"myQueue1", "myQueue2"};
    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    public MessageService(AmqpAdmin amqpAdmin,AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
        //创建Exchange对象，根据Exchange类型的不同
        //可使用DirectExchange、FanoutExchange、HeadersExchange、FanoutExchange
        FanoutExchange exchange = new FanoutExchange(EXCHANGE_NAME, true, true);
        //声明Exchange
        this.amqpAdmin.declareExchange(exchange);
        //使用循环声明并绑定两个队列
        for (String queueName : QUEUE_NAME) {
            Queue queue = new Queue(queueName, true, false, true);
            //声明队列
            this.amqpAdmin.declareQueue(queue);
            Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "", null);
            this.amqpAdmin.declareBinding(binding);
        }
    }

    public void produce(String message) {
        this.amqpTemplate.convertAndSend(EXCHANGE_NAME, "", message);
    }
}
