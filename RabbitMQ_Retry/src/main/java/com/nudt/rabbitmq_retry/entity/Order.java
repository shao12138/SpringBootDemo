package com.nudt.rabbitmq_retry.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Table(name = "Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 9111357402963030257L;
    @Column
    @IsKey
    @TableId(type = IdType.AUTO)
    private String id;
    @Column
    private String name;
    @Column
    private String messageId;

}
