package com.nudt.rabbitmq_retry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@Table(name = "Message")
public class BrokerMessageLog {
    /**
     * 消息uid
     */
    @Column
    @IsKey
    @TableId(type = IdType.AUTO)
    private String messageId;

    /**
     * 消息内容（JSON保存）
     */
    @Column
    private String message;

    /**
     * 重试次数，阈值：3
     */
    @Column
    private Integer tryCount;

    /**
     * 消息状态，0：未发送成功、1：发送成功、2：失败消息
     */
    @Column
    private String status;

    /**
     * 超时时间（下次重发时间）
     */
    @Column
    private Date nextRetry;

    /**
     * 创建时间
     */
    @Column
    private Date createTime;

    /**
     * 更新时间
     */
    @Column
    private Date updateTime;

}
