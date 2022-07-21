package com.share1024.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Description
 * @Date 2022年07月21日
 * @Created by yesheng
 */
public class ConcurrencyTest {


    @Test
    public void listenableFuture() throws IOException {
        ListeningExecutorService listenableFuture = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture future = listenableFuture.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        future.addListener(()->{
            System.out.println("sleep end call back");
        },listenableFuture);
        System.in.read();
    }


    @Test
    public void java8Future() throws IOException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        completableFuture.thenApply((id)->{
            System.out.println("id:"+id);
            return id * id;
        }).whenComplete((result,e)->{
            System.out.println(result);
        });
        System.in.read();
    }
}
