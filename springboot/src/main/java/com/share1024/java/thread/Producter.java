package com.share1024.java.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/1 16:50
 * \* Description:
 * \
 */
public class Producter implements Runnable{
    private AtomicInteger product;

    public Producter(AtomicInteger product){
        this.product = product;
    }
    @Override
    public void run() {
        while (true){
            int result = product.incrementAndGet();
                System.out.println("生成一个商品，剩余 " + result);
                product.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }
}