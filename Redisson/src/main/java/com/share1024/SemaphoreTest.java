package com.share1024;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * \* @Author: yesheng
 * \* Date: 2021/7/23 15:38
 * \* Description:
 * \
 */
public class SemaphoreTest {

    @Test
    public void test(){
        Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire(1);
            System.out.println("===");
            semaphore.acquire(1);
            System.out.println("===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}