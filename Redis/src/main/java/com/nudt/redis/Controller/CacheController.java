package com.nudt.redis.Controller;

import com.nudt.redis.Pojo.User;
import com.nudt.redis.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Cache")
public class CacheController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/testCache")
    public void testCache() {
        User user = userRepository.getUserByName("燕双嘤");
        System.out.println("name: " + user.getName() + ",age:" + user.getAge());

        user = userRepository.getUserByName("燕双嘤");
        System.out.println("name: " + user.getName() + ",age:" + user.getAge());
    }
}
