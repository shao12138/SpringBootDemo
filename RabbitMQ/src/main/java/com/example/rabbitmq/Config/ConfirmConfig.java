package com.example.rabbitmq.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {
    @Bean
    public Queue confirmQueue() {
        return new Queue("rabbit_confirm_queue");
    }
    @Bean
    public DirectExchange confirmExchange() {
        return new DirectExchange("confirm_direct_exchange");
    }
    @Bean
    public Binding confirmFanoutExchangeBing() {
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with("rabbit_confirm_queue");
    }
}