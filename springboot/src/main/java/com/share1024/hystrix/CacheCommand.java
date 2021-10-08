package com.share1024.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 *
 *
 * @author shengye
 * @Title
 * @Date 2021/9/7 11:10
 */
public class CacheCommand extends HystrixCommand<String> {

    private String cacheKey;

    @Override
    protected String getCacheKey() {
        return cacheKey;
    }

    protected CacheCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name)));
        this.cacheKey = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("yesheng");
        return "yesheng";
    }


    public static void main(String[] args) {

        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        CacheCommand cacheCommand1 = new CacheCommand("yesheng");
        CacheCommand cacheCommand2 = new CacheCommand("yesheng");
        CacheCommand cacheCommand3 = new CacheCommand("yesheng");

        new Thread(()->{
            System.out.println("result 1 "+cacheCommand1.execute());
            System.out.println("result 1 "+cacheCommand2.execute());
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println("result 2 "+cacheCommand3.execute());

        }).start();

    }
}
