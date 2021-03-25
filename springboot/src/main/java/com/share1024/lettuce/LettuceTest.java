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
        RedisClient redisClient = RedisClient.create("redis://9iedyIcX51v109MX@m2-3379-gznx-InteractiveGameTest-dev.redis.imdmx.com:3379/0");
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();
        commands.set("test","hello world");
        System.out.println(commands.get("test"));
        commands.del("test");
        redisConnection.close();
        redisClient.shutdown();
    }
}