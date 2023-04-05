package com.nudt.rabbitmq_retry.config;

import java.util.Date;
import java.util.List;


import com.nudt.rabbitmq_retry.constant.Constants;
import com.nudt.rabbitmq_retry.entity.BrokerMessageLog;
import com.nudt.rabbitmq_retry.entity.Order;
import com.nudt.rabbitmq_retry.mapper.BrokerMessageLogMapper;
import com.nudt.rabbitmq_retry.producer.RabbitOrderSender;
import com.nudt.rabbitmq_retry.utils.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetryMessageTasker {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    public void reSend() {
        // 取出 status=0 且响应超时的消息
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(messageLog -> {
            if (messageLog.getTryCount() >= 3) {
                // 重试3次，更新消息状态 status=2
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                // 重新发送，tryCount+1
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(), new Date());
                Order reSendOrder = FastJsonConvertUtil.convertJSONToObject(messageLog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.sendOrder(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
    }
}

