package com.share1024.hystrix.thread;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservable;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

/**
 * \* @Author: yesheng
 * \* Date: 2021/6/21 11:13
 * \* Description:
 * \
 */
public class ProductOne2 extends HystrixObservableCommand<String> {

    public ProductOne2(){
        super(HystrixCommandGroupKey.Factory.asKey("ProductOne2"));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            System.out.println(Thread.currentThread().getName());
            subscriber.onNext("ProductOne2");
        });
    }

    public static void main(String[] args) {
        HystrixObservable<String> hystrixCommand1 = new ProductOne();
        HystrixObservable<String> hystrixCommand2 = new ProductOne2();
        System.out.println(hystrixCommand1.observe());
        hystrixCommand1.observe();
        hystrixCommand2.observe();
    }
}