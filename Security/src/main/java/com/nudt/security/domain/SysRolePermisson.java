package com.nudt.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermisson implements Serializable {
    //角色
    private Long roleId;
    private String roleName;
    //权限
    private Long permissionId;
    private String url;
}
