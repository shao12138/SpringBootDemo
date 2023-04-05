package com.tooi.demorabbitmqretry.config.task;

import java.util.Date;
import java.util.List;

import com.tooi.demorabbitmqretry.constant.Constants;
import com.tooi.demorabbitmqretry.entity.BrokerMessageLog;
import com.tooi.demorabbitmqretry.entity.Order;
import com.tooi.demorabbitmqretry.mapper.BrokerMessageLogMapper;
import com.tooi.demorabbitmqretry.producer.RabbitOrderSender;
import com.tooi.demorabbitmqretry.utils.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Tooi
 * @date 2020/8/24 17:38
 * @description
 */
@Component
public class RetryMessageTasker {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
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

