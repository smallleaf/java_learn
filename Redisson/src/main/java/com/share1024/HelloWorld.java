package com.share1024;

import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

    Config config = new Config();

    private String lockKey = "test";

    @Before
    public void before(){
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("").setPassword("");
        config.setLockWatchdogTimeout(3000);
    }

    /**
     * 每隔一段时间1s，自动续时间
     */
    @Test
    public void lock(){
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(lockKey);
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("+++");
    }

    @Test
    public void testLock(){
        new Thread(()->{
            RedissonClient client = Redisson.create(config);
            RLock lock = client.getLock(lockKey);
            lock.lock();
        }).start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lockTimeOut(){
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(lockKey);
        lock.lock(20,TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("+++");
    }

    @Test
    public void tryLock() throws InterruptedException {
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(lockKey);
        lock.lock(200,TimeUnit.SECONDS);
        boolean res = Redisson.create(config).getLock(lockKey).tryLock(50,10,TimeUnit.SECONDS);
        if(res){
            try {

            }finally {
                lock.unlock();
            }
        }
    }

    @Test
    public void asyncLock() throws ExecutionException, InterruptedException {
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(lockKey);
        RFuture<Void> future = lock.lockAsync();
        future.get();
        TimeUnit.SECONDS.sleep(10);
        lock.unlock();
    }
}
