package com.example.rabbitmq.ACK;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AckProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 配置消息确认机制
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         *
         * @param correlationData 消息相关的数据，一般用于获取唯一标识ID
         * @param b 是否发送成功
         * @param error 失败原因
         */
        public void confirm(CorrelationData correlationData, boolean b, String error) {
            if (b) {
                System.out.println("confirm 消息发送确认成功...消息ID为：" + correlationData.getId());
            } else {
                System.out.println("confirm 消息发送确认失败...消息ID为：" + correlationData.getId() + " 失败原因: " + error);
            }
        }
    };

    private final RabbitTemplate.ReturnsCallback returnsCallback = new RabbitTemplate.ReturnsCallback() {
        @Override
        public void returnedMessage(ReturnedMessage returnedMessage) {
            System.out.println("return exchange: " + returnedMessage.getExchange() + ", routingKey: "
                    + returnedMessage.getRoutingKey() + ", replyCode: " + returnedMessage.getReplyCode() + ", replyText: " + returnedMessage.getReplyText());
        }
    };

    /**
     * 发送消息的参数有交换机、空路由键、消息，并设置一个唯一消息ID
     */
    @RequestMapping("/ack")
    public void sendConfirm() {
        rabbitTemplate.convertAndSend("confirm_direct_exchange",
                "rabbit_confirm_queue",
                "这是一个带confirm的消息",
                new CorrelationData("" + System.currentTimeMillis()));
        // 使用上方配置的发送回调方法
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnsCallback);
    }
}
