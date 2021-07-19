package com.share1024;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/12 11:48
 * \* Description:
 * \
 */
public class Test1 {

    private AtomicInteger atomicInteger = new AtomicInteger();

    public void addNum(int num){
        System.out.println(atomicInteger.getAndAdd(num));
    }

    public static void main(String[] args) throws IOException {
        Test1 test1 = new Test1();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (int i = 0; i < 100; i++) {
            scheduler.submit(()->{
               test1.addNum(2);
            });
        }

        System.in.read();
    }
}