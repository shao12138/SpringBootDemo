package com.nudt.security.repository;

import com.nudt.security.domain.SysPermission;
import com.nudt.security.domain.SysRole;
import com.nudt.security.domain.SysRolePermisson;
import com.nudt.security.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRepository {

    /**
     * 通过username查找 user
     * username是唯一的前提
     */
    @Select("select id,password,username from sys_user WHERE username=#{username}")
    SysUser findUserByUsername(String username);

    /**
     * 通过用户名 查找·
     */
    @Select("select sr.id,sr.name from sys_user as su join sys_role as sr where su.username=#{username}")
    List<SysRole> findRolesByUsername(String username);

    /**
     * 通过用户名 查找权限
     */
    @Select("select sp.* from sys_user su left join sys_user_role  sur on su.id = sur.sys_user_id " +
            "left join sys_role_permission srp on sur.sys_role_id = srp.sys_role_id" +
            "left join sys_permission sp on srp.sys_permission_id = sp.id" +
            "where su.username =#{username}")
    List<SysPermission> findPermissionsByUsername(String username);

    @Select("select sr.id,sr.name,sp.id,sp.url from sys_role as sr join sys_permission as sp")
    List<SysRolePermisson> findAllRolePermissoin();
}
