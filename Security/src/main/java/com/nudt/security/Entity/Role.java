package com.nudt.security.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

// 角色实体类
@Data
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private List<Permission> permissions;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
