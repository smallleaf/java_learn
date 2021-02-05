package com.share1024.java.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* @Author: yesheng
 * \* Date: 2021/1/20 17:25
 * \* Description:
 * \
 */
public class ConditionTest {
    Lock lock = new  ReentrantLock();
    Condition condition = lock.newCondition();
    int i = 1;
    public void await(){
        lock.lock();
        try {
            condition.await();
            System.out.println("i am wait " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void test(){
        lock.lock();
        try {
            Thread.sleep(5000);
            System.out.println("i am test");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void signal(){
        i = 3;
        System.out.println("i am signal");
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        new Thread(()->{
            conditionTest.await();
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            conditionTest.test();
        }).start();
        Thread.sleep(1000);
        conditionTest.signal();
        Thread.sleep(1000);
        System.out.println("end");
    }
}