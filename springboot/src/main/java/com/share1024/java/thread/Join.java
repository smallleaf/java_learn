package com.share1024.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/1 17:12
 * \* Description:
 * \
 */
public class Join implements Runnable{
    private Thread thread;

    public Join(Thread thread){
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +".结束");
    }

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < 100; i++) {
            Thread thread1 = new Thread(new Join(thread));
            thread1.start();
            thread = thread1;
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +".结束");
    }
}