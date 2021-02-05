package com.share1024.java.thread;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/1 17:18
 * \* Description:
 * \
 */
public class ThreadInterrupte implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(5000);
                System.out.println("ThreadInterrupte " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadInterrupte());
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}