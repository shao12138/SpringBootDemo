package com.nudt.rabbitmq_retry.utils;

import com.alibaba.fastjson.JSON;
import com.nudt.rabbitmq_retry.entity.Order;

public class FastJsonConvertUtil {
    public static String convertObjectToJSON(Object object) {
        return JSON.toJSONString(object);
    }

    public static Order convertJSONToObject(String message, Class<Order> Class) {
        return JSON.parseObject(message, Class);
    }
}
