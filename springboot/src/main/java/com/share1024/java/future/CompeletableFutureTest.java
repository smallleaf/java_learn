package com.share1024.java.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Date 2023年09月24日
 * @Created by yesheng
 */
public class CompeletableFutureTest {

    @Test
    public void test() throws InterruptedException {
        CompletableFuture.runAsync(()->{
            System.out.println("-----runAsync-----");
        }).thenRun(()->{
            System.out.println("-----thenRun-----");
        }).thenApply((v)->{
            System.out.println("-----thenApply-----");
            return "thenApply";
        }).thenAccept((v)->{
            System.out.println("-----thenAccept-----");
        }).thenCompose((v)->{
            System.out.println("-----thenCompose-----");
            return CompletableFuture.completedFuture("thenCompose");
        }).thenCombine(CompletableFuture.completedFuture("thenCombine"),(v1,v2)->{
            System.out.println("-----thenCombine-----");
            return "thenCombine";
        }).thenAcceptBoth(CompletableFuture.completedFuture("thenAcceptBoth"),(v1,v2)->{
            System.out.println("-----thenAcceptBoth-----");
        }).whenComplete((v,e)->{
            System.out.println("-----whenComplete-----");
        }).exceptionally((e)->{
            System.out.println("-----exceptionally-----");
            return null;
        }).handle((v,e)->{
            System.out.println("-----handle-----");
            return null;
        }).join();
        System.out.println("-----end-----");
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture time = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("-----runAsync-timw----");
            return "time ok";
        });

        CompletableFuture t = CompletableFuture.supplyAsync(()->{
            System.out.println("-----runAsync-----");
            return "ok";
        }).thenCombine(time,(v1,v2)-> "1:" + v1 + " 2:" + v2).thenAccept((v)->{
            System.out.println("-----thenAccept-----" + v);
        }).thenApply(v->"233");
        Object o = t.get(10,TimeUnit.SECONDS);
        System.out.println("===");
    }
}
