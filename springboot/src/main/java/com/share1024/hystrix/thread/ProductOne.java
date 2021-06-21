package com.share1024.hystrix.thread;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

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
}