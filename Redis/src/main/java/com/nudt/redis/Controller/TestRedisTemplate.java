package com.nudt.redis.Controller;

import com.nudt.redis.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/Redis")
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test")
    public void test() {
        // 调用set()方法创建缓存
        redisTemplate.opsForValue().set("name", "燕双嘤");
        System.out.println("name: " + redisTemplate.opsForValue().get("name"));
    }

    @RequestMapping("/testUser")
    public void testUser() {
        User user = new User();
        user.setName("燕双嘤");
        user.setPassword("123456");
        user.setAge(30);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 调用set()方法创建缓存
        operations.set("燕双嘤", user);
        // 调用get()方法获取数据
        User u = operations.get("燕双嘤");
        System.out.println(u.toString());
    }

    @RequestMapping("/deleteUser")
    public void deleteUser() {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 删除缓存
        redisTemplate.delete("燕双嘤");
        // 判断key是否存在
        boolean exists = redisTemplate.hasKey("燕双嘤");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }

    @RequestMapping("/expireUser")
    public void expireUser() {
        User user = new User();
        user.setName("燕双嘤");
        user.setAge(30);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 创建缓存并设置缓存失效时间
        operations.set("燕双嘤", user, 10000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 10秒后判断缓存是否存在
        boolean exists = redisTemplate.hasKey("燕双嘤");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 10秒后判断缓存是否存在
        exists = redisTemplate.hasKey("燕双嘤");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }
}