package com.example.rabbitmq.ACK;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AckConsumer {
   //@RabbitListener(queues = "rabbit_confirm_queue")
    public void process(Message message, Channel channel) throws IOException, InterruptedException {
        try {
            System.out.println("正常收到消息：" + new String(message.getBody()));
            int i = 1 / 0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列，则进入死信队列
                channel.basicReject(message.getMessageProperties()
                        .getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理");
                // requeue为true时重新入队
                channel.basicNack(message.getMessageProperties().
                        getDeliveryTag(), false, true);
            }
        }
    }
}