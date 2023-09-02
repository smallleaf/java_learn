package com.share1024.java.java8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2023年05月30日
 * @Created by yesheng
 */
public class CallerRunsPolicyTest {

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue);

        // 设置拒绝策略为 CallerRunsPolicy
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 提交任务到线程池
        for (int i = 0; i < 100; i++) {
            threadPool.submit(()->{
                try {
                    //睡眠一分钟
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("handle " + i);
        }

    }
}
