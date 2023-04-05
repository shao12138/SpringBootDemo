package com.example.rabbitmq.Confirm;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConfirmConsumer {
    //@RabbitListener(queues = "rabbit_confirm_queue")
    public void aa(Message message, Channel channel) throws IOException {
        try {
            System.out.println("正常收到消息：" + new String(message.getBody()));
            int a = 1 / 0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 两个布尔值，若第二个设为 false，则丢弃该消息；若设为true，则返回给队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("消费失败 我此次将返回给队列");
        }
    }
}