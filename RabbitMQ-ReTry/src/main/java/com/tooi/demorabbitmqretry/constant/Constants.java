package com.tooi.demorabbitmqretry.constant;

/**
 * @author Tooi
 * @date 2020/8/24 17:07
 * @description
 */
public class Constants {
    public static final String ORDER_SENDING = "0"; //发送中

    public static final String ORDER_SEND_SUCCESS = "1"; //成功

    public static final String ORDER_SEND_FAILURE = "2"; //失败

    public static final int ORDER_TIMEOUT = 1; /*分钟超时单位：min*/
}
