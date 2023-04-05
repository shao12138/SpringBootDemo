package com.tooi.demorabbitmqretry.entity;

import java.util.Date;

/**
 * @author Tooi
 * @date 2020/8/24 16:50
 * @description
 */
public class BrokerMessageLog {
    /**
     * 消息uid
     */
    private String messageId;

    /**
     * 消息内容（JSON保存）
     */
    private String message;

    /**
     * 重试次数，阈值：3
     */
    private Integer tryCount;

    /**
     * 消息状态，0：未发送成功、1：发送成功、2：失败消息
     */
    private String status;

    /**
     * 超时时间（下次重发时间）
     */
    private Date nextRetry;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
