package com.share1024.java.jvm.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/16 19:41
 * \* Description:
 * \
 */
public class ThreadWait {

    public static void createBusyThread(){
        new Thread(()->{
            while (true){}
        },"testBusyWait").start();
    }

    public static void createLockThread(final Object lock){
        new Thread(()->{
            synchronized (lock){
                try {
                    lock.wait();
                }catch (Exception e){

                }
            }
        },"testLockWait").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        createBusyThread();
        bufferedReader.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}