package com.tooi.demorabbitmqretry.entity;

import java.io.Serializable;

/**
 * @author Tooi
 * @date 2020/8/24 16:49
 * @description
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 9111357402963030257L;

    private String id;

    private String name;

    private String messageId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }
}
