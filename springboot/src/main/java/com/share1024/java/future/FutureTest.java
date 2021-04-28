package com.share1024.java.future;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/28 11:39
 * \* Description:
 * \
 */
public class FutureTest {

    @Test
    public void futureTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(()->{
            TimeUnit.SECONDS.sleep(10);
            return 1;
        });
        System.out.println("i do other things");

        try {
            Integer result = future.get(2,TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("time out cannot  get the result ");
            try {
                System.out.println(future.get(10,TimeUnit.SECONDS));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void test(){
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        completableFuture.whenComplete((k,v)->{
            System.out.println(k+"_"+v);
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}