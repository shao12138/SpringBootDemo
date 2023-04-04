package com.nudt.redis.Repository;

import com.nudt.redis.Pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    //@Cacheable应用到读取数据的方法上，先从缓存中读取，如果没有，再从DB获取数据，然后把数据添加到缓存中unless表示条件表达式成立的话不放入缓存
    @Cacheable(value = "user")
    public User getUserByName(String username) {
        User user = new User();
        user.setName(username);
        user.setAge(30);
        user.setPassword("123456");
        System.out.println("user info from database");
        return user;
    }
}
