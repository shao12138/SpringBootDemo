package com.nudt.rabbitmq_retry.producer;

import java.util.Date;

import com.nudt.rabbitmq_retry.config.RetryMessageTasker;
import com.nudt.rabbitmq_retry.constant.Constants;
import com.nudt.rabbitmq_retry.entity.Order;
import com.nudt.rabbitmq_retry.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RetryMessageTasker retryMessageTasker;
    /**
     * ACK回调函数
     */
    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if (ack) {
                //返回成功 则进行更新消息日志 status=1
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                retryMessageTasker.reSend();
                System.err.println("异常处理...");
            }
        }
    };

    /**
     * 发送消息，设置回调函数
     * @param order
     * @throws Exception
     */
    public void sendOrder(Order order) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange1", "order.ABC", order, correlationData);
    }
}

