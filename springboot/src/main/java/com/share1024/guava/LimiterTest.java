package com.share1024.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/29 16:04
 * \* Description:
 * \
 */
public class LimiterTest {

    /**
     * 创建了5个令牌，大概200ms生成一个令牌
     */
    @Test
    public void limit(){
        RateLimiter limiter = RateLimiter.create(5);
        for (int i = 0; i < 200; i++) {
            long now = System.currentTimeMillis();
            limiter.acquire();
            System.out.println(System.currentTimeMillis() - now);
        }
    }

    @Test
    public void test(){
        RateLimiter limiter = RateLimiter.create(5,10,TimeUnit.SECONDS);
        for (int i = 0; i < 200; i++) {
            long now = System.currentTimeMillis();
            limiter.acquire();
            System.out.println(System.currentTimeMillis() - now);
        }
    }
}