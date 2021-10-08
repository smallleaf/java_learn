package com.share1024.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/9 20:28
 * \* Description:
 * \
 */
public class GetOrderObCommand extends HystrixObservableCommand<String> {

    public GetOrderObCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test")));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.just("yesheng");
    }

    public static void main(String[] args) {
        GetOrderObCommand getOrderObCommand = new GetOrderObCommand();
        getOrderObCommand.observe().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }
}