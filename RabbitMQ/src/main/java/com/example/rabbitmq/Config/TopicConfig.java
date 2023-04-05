package com.example.rabbitmq.Config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;

@Configuration
public class TopicConfig {
    final static String message = "topic.color";
    final static String message2 = "topic.color.red";
    final static String message3 = "topic.msg.feedback";

    // 定义队列
    @Bean
    public Queue queueMessage() {
        return new Queue(TopicConfig.message);
    }

    @Bean
    public Queue queueMessage2() {
        return new Queue(TopicConfig.message2);
    }

    @Bean
    public Queue queueMessage3() {
        return new Queue(TopicConfig.message3);
    }

    // 交换机
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    // 将队列和交换机绑定
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.color.*");
    }

    @Bean
    Binding bindingExchangeMessage2(Queue queueMessage2, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage2).to(exchange).with("topic.color.red");
    }

    @Bean
    Binding bindingExchangeMessage3(Queue queueMessage3, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage3).to(exchange).with("topic.msg.*");
    }
}