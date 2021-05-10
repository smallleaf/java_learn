package com.share1024.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.junit.Test;

import java.time.Duration;

/**
 * \* @Author: yesheng
 * \* Date: 2021/3/24 18:58
 * \* Description:
 * \
 */
public class LettuceTest {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:7001");
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();
        commands.zadd("test",System.currentTimeMillis(),"test");
        System.out.println(commands.zrank("test","test"));
        System.out.println(commands.zrank("test","test2"));
        redisConnection.close();
        redisClient.shutdown();
    }

    @Test
    public void testLua(){
        RedisURI redisURI =
                RedisURI.builder().withHost("m2-3379-gznx-InteractiveGameTest-dev.redis.imdmx.com").withPort(3379).withPassword("9iedyIcX51v109MX").build();
        RedisClient redisClient = RedisClient.create(redisURI);
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();
        String script = "local key=KEYS[1]\n" +
                "local timeOut = ARGV[1]\n" +
                "\n" +
                "if redis.call(\"EXISTS\",key) == 0 then\n" +
                "\tredis.call(\"SET\",key,key)\n" +
                "\tredis.call(\"expire\",key,timeOut)\n" +
                "\treturn true\n" +
                "end\n" +
                "return false\n";
        String scriptSha = commands.scriptLoad(script);
        System.out.println(scriptSha);
        boolean result = commands.evalsha(scriptSha, ScriptOutputType.BOOLEAN,new String[]{"test"},"100");
        System.out.println(result);
    }

    @Test
    public void  testCluster(){
        RedisClusterClient redisClient = RedisClusterClient.create("redis://localhost:7000,localhost:7001,localhost:7002");
        StatefulRedisClusterConnection<String, String>  redisClusterConnection = redisClient.connect();
        RedisAdvancedClusterCommands<String,String> commands = redisClusterConnection.sync();
        commands.set("yesheng","哈哈哈");
        System.out.println(commands.get("yesheng"));

    }
}