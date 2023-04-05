package com.example.rabbitmq.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {
    //队列一
    @Bean
    public Queue directQueueQ1() {
        return new Queue("direct.Q1");
    }

    //队列二
    @Bean
    public Queue directQueueQ2() {
        return new Queue("direct.Q2");
    }

    //队列三
    @Bean
    public Queue directQueueQ3() {
        return new Queue("direct.Q3");
    }

    //定义交换机 Direct类型
    @Bean
    public DirectExchange myDirectExchange() {
        return new DirectExchange("directExchange");
    }

    //队列绑定到交换机，再指定一个路由键
    //directQueueOne() 会找到上方定义的队列Bean
    @Bean
    public Binding DirectExchangeQ1() {
        return BindingBuilder.bind(directQueueQ1()).to(myDirectExchange()).with("direct.Q1");
    }

    //队列绑定到交换机，再指定一个路由键
    @Bean
    public Binding DirectExchangeQ2() {
        return BindingBuilder.bind(directQueueQ2()).to(myDirectExchange()).with("direct.Q2");
    }

    //队列绑定到交换机，再指定一个路由键
    @Bean
    public Binding DirectExchangeQ3() {
        return BindingBuilder.bind(directQueueQ2()).to(myDirectExchange()).with("direct.Q3");
    }
}
