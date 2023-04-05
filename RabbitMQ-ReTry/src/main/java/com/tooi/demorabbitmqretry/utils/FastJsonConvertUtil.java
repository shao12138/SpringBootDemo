package com.tooi.demorabbitmqretry.utils;

import com.alibaba.fastjson.JSON;
import com.tooi.demorabbitmqretry.entity.Order;

/**
 * @author Tooi
 * @date 2020/8/24 17:36
 * @description
 */
public class FastJsonConvertUtil {
    public static String convertObjectToJSON(Object object) {
        return JSON.toJSONString(object);
    }

    public static Order convertJSONToObject(String message, Class<Order> Class) {
        return JSON.parseObject(message, Class);
    }
}
