package com.share1024.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/1 17:00
 * \* Description:
 * \
 */
public class Consumer implements Runnable {

    private AtomicInteger product;

    public Consumer(AtomicInteger product){
        this.product = product;
    }

    @Override
    public void run() {
        while(true){
            if(product.get() <= 0){
                try {
                    product.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}