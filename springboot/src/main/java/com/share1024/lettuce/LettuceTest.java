package com.share1024.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;

/**
 * \* @Author: yesheng
 * \* Date: 2021/3/24 18:58
 * \* Description:
 * \
 */
public class LettuceTest {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:3379/0");
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();
        commands.zadd("test",System.currentTimeMillis(),"test");
        System.out.println(commands.zrank("test","test"));
        System.out.println(commands.zrank("test","test2"));
        redisConnection.close();
        redisClient.shutdown();
    }
}