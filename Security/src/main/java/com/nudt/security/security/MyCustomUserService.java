package com.nudt.security.security;

import com.nudt.security.domain.SysRole;
import com.nudt.security.domain.SysUser;
import com.nudt.security.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyCustomUserService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(MyCustomUserService.class);
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 通过验证 将用的所有角色 用户信息中
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("根据名称获取用户信息： username is {}", username);

        SysUser user = sysUserRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        //获取所有请求的url
        //List<SysPermission> sysPermissions = sysUserMapper.findPermissionsByUsername(user.getUsername());
        List<SysRole> sysRoles = sysUserRepository.findRolesByUsername(user.getUsername());

        logger.info("用户角色个数为{}", sysRoles.size());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            //封装用户信息和角色信息 到 SecurityContextHolder全局缓存中
            logger.info("name--->{}", sysRole.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
