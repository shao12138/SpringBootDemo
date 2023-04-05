package com.tooi.demorabbitmqretry;

import com.tooi.demorabbitmqretry.entity.Order;
import com.tooi.demorabbitmqretry.producer.RabbitOrderSender;
import com.tooi.demorabbitmqretry.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DemoRabbitmqRetryApplicationTests {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private OrderService orderService;


    @Test
    void contextLoads() throws Exception {
        Order order = new Order();
        order.setId("2020082400001");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        rabbitOrderSender.sendOrder(order);
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("2020082400002");
        order.setName("测试创建订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }

}
