package com.nudt.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Lettuce_Test {

    static RedisClient redisClient;
    static StatefulRedisConnection<String, String> conn;

    // 初始化RedisClient、StatefulRedisConnection的方法
    public static void init() {
        // 创建RedisURI对象
        RedisURI redisUri = RedisURI.builder()
                .withHost("101.35.188.205")
                .withPassword(new char[]{'3', '2', '1', '4', '7'})
                .withDatabase(0)
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        // 创建RedisClient
        redisClient = RedisClient.create(redisUri);
        // 获取StatefulRedisConnection
        conn = redisClient.connect();
    }

    public static void closeResource() {
        // 关闭StatefulRedisConnection
        conn.close();
        // 关闭RedisClient
        redisClient.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        //创建反应式模式RedisReactiveCommands
        RedisReactiveCommands<String,String> redisCommands = conn.reactive();
        //执行Ping命令
        Mono<String> result = redisCommands.ping();
        result.subscribe(System.out::println);
        //执行zadd命令
        redisCommands.zadd("key1",0.3,"Java",0.4,"C++",0.5,"Python").subscribe(System.out::println);
        //执行zrank命令
        redisCommands.zrank("key1","ysy").subscribe(System.out::println);
        //执行zrange命令
        redisCommands.zrange("key1",1,2).subscribe(System.out::println);
        //执行zpopmax命令
        redisCommands.zpopmax("key1").subscribe(System.out::println);
        Thread.sleep(500);
        closeResource();
    }
}
