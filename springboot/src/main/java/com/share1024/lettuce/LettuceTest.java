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

import java.math.BigDecimal;
import java.text.NumberFormat;
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
    public void testRank2(){
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();

        //相同分数，时间晚的排在后面

        String key ="rank";

        long time1 = System.currentTimeMillis();

        long time2 = time1 + 1000;

        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String memer1 = nf.format(Math.pow(10,13) - time1) +":" +"u0000000000001";
        String memer2 = nf.format(Math.pow(10,13) - time2) +":" +"u0000000000002";
        commands.zadd(key,100,memer1);
        commands.zadd(key,100,memer2);
        redisConnection.close();
        redisClient.shutdown();
    }


    @Test
    public void testRank(){
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        redisClient.setDefaultTimeout(Duration.ofSeconds(10000));
        StatefulRedisConnection<String, String> redisConnection = redisClient.connect();
        RedisCommands<String,String> commands = redisConnection.sync();

        //相同分数，时间晚的排在后面

        String key ="rank";

        long time1 = System.currentTimeMillis();

        long time2 = time1 + 1000;

        double score = 9999;

//        double score1 = score + (Math.pow(10,14) - (double)time1) / Math.pow(10,14);
//        double score2 = score + (Math.pow(10,14) -(double)time2) / Math.pow(10,14);
        double score1 = score * Math.pow(10,13) - time1;
        double score2 = score * Math.pow(10,13) - time2;
        commands.zadd(key,score1,"user1");

        commands.zadd(key,score2,"user2");
        redisConnection.close();
        redisClient.shutdown();
    }

    @Test
    public void testLua(){
        RedisURI redisURI =
                RedisURI.builder().withHost("").withPort(3379).withPassword("").build();
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
        RedisClusterClient redisClient = RedisClusterClient.create("redis://127.0.0.1:7001");
        StatefulRedisClusterConnection<String, String> redisClusterConnection = redisClient.connect();
        RedisAdvancedClusterCommands<String,String> commands = redisClusterConnection.sync();
        commands.set("yesheng","哈哈哈");
        System.out.println(commands.get("yesheng"));

    }
}
