package com.share1024.hystrix.thread;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

import java.util.regex.Pattern;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/21 11:13
 * \* Description:
 * \
 */
public class ProductOne extends HystrixObservableCommand<String> {

    public ProductOne(){
        super(HystrixCommandGroupKey.Factory.asKey("ProductOne"));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            System.out.println(Thread.currentThread().getName());
            subscriber.onNext("ProductOne");
        });
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String test = "test:"+1;
        }
        System.out.println(System.currentTimeMillis() - now);
    }
}