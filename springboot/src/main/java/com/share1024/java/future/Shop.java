package com.share1024.java.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * \* @Author: yesheng
 * \* Date: 2021/4/28 11:55
 * \* Description:
 * \
 */
public class Shop {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            List<Shop> shops = Arrays.asList(new Shop(),new Shop(),new Shop(),new Shop());
            long start = System.currentTimeMillis();
                    shops.parallelStream().map(shop -> CompletableFuture.supplyAsync(()->shop.test()))
                            .map(future->future.thenApply(String::valueOf)).collect(Collectors.toList());
            System.out.println(System.currentTimeMillis()-start);
        }

    }


    public Double test(){
        delay();
        return 0D;
    }
    public Future<Double> getPrice(String product){
//        new Thread(()->{
//            try {
//                completableFuture.complete(calculatePrice(product));
//            }catch (Exception e){
//                completableFuture.completeExceptionally(e);
//            }
//        }).start();
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    private void delay(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private double calculatePrice(String product){
        delay();
        int i= 1/0;
        return 1;
    }
}