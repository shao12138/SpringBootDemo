package com.nudt.security.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 角色权限实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermisson implements Serializable {

    private Long roleId;
    private String roleName;
    private Long permissionId;
    private String url;
}