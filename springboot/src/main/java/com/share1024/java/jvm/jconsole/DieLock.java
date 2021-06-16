package com.share1024.java.jvm.jconsole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/16 19:49
 * \* Description:
 * \
 */
public class DieLock {
    static class DieRunnable implements Runnable{
        int a,b;
        public DieRunnable(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100; i++) {
            new Thread(new DieRunnable(1,2)).start();
            new Thread(new DieRunnable(2,1)).start();
        }
        System.in.read();
        System.out.println("===");
    }
}