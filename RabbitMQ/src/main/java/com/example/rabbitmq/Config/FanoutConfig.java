package com.example.rabbitmq.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    // 定义队列
    @Bean
    public Queue Q1Message() {
        return new Queue("fanout.Q1");
    }

    @Bean
    public Queue Q2Message() {
        return new Queue("fanout.Q2");
    }

    @Bean
    public Queue Q3Message() {
        return new Queue("fanout.Q3");
    }

    // 定义交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    // 分别进行绑定
    @Bean
    Binding bindingExchangeQ1(Queue Q1Message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(Q1Message).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeQ2(Queue Q2Message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(Q2Message).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeQ3(Queue Q3Message, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(Q3Message).to(fanoutExchange);
    }
}
