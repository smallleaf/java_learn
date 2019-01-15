package com.share1024.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/5
 */
public class RedisUtil {

    private static final String host = "scstrds01.rmz.gomo.com";

    private static final int prot = 6379;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    private static JedisPool jedisPool = null;

    static{

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMaxTotal(MAX_ACTIVE);
        poolConfig.setMaxWaitMillis(MAX_WAIT);
        jedisPool = new JedisPool(poolConfig,host,prot,TIMEOUT);

    }


    public static Long setntx(String key,String value){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.setnx(key,value);
        }
    }

    public static void expire(String key,int seconds){
        try(Jedis jedis = jedisPool.getResource()){
             jedis.expire(key,seconds);
        }
    }



    public static void del(String key){
        try(Jedis jedis = jedisPool.getResource()){
            jedis.del(key);
        }
    }

    public static String get(String key){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.get(key);
        }
    }


    public static String getSet(String key,String value){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.getSet(key,value);
        }
    }


    public static String set(String key, String value, String nxxx, String expx, long time){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.set(key, value, nxxx, expx, time);
        }
    }


    public static Object eval(String script, List<String> keys, List<String> args){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.eval(script,keys,args);
        }
    }



    public static Double zincrby(String key, double score, String member){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.zincrby(key, score, member);
        }
    }


    public static Long lpush(String key,String member){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.lpush(key,member);
        }
    }


    public static String lpop(String key){
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.lpop(key);
        }
    }


    public static void set(String key,String value){
        try(Jedis jedis = jedisPool.getResource()){
             jedis.set(key,value);
        }
    }


    public static Jedis getJedis(){
        return jedisPool.getResource();
    }



}
