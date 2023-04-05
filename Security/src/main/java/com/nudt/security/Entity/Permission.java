package com.nudt.security.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


// 权限实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long pid;
    private String name;
    private String url;
    private String description;
}
