package com.nudt.security.config;

import com.nudt.security.security.MyCustomUserService;
import com.nudt.security.util.Md5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 通过 实现UserDetailService 来进行验证
     */
    @Autowired
    private MyCustomUserService myCustomUserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //校验用户
        //校验密码
        auth.userDetailsService(myCustomUserService).passwordEncoder(new PasswordEncoder() {

            public String encode(CharSequence rawPassword) {
                return Md5Util.MD5(String.valueOf(rawPassword));
            }

            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(Md5Util.MD5(String.valueOf(rawPassword)));
            }
        });
    }

    /**
     * 创建自定义的表单
     * 页面、登录请求、跳转页面等
     */
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "index", "/login", "/css/**", "/js/**")//允许访问
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//拦截后get请求跳转的页面
                .defaultSuccessUrl("/content")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}