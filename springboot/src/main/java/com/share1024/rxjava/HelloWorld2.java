package com.share1024.rxjava;

import rx.Notification;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 *
 *
 * @author shengye
 * @Title
 * @Date 2021/9/7 10:39
 */
public class HelloWorld2 {

    public static void main(String[] args) {


        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                System.out.println("defer test");
                return Observable.defer(new Func0<Observable<String>>() {
                    @Override
                    public Observable<String> call() {
                        System.out.println("dep test");
                        throw new NullPointerException();
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("errorqqq");
                    }
                });
            }
        });

        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("next " + s);
                throw new NullPointerException();
            }
        };

        Action0 action0 = new Action0() {
            @Override
            public void call() {
                System.out.println("==doOnCompleted=");
            }
        };


        observable.doOnNext(action1)
                    .doOnCompleted(action0)
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("errrrrrrrrrrrrrr");
                    }
                })
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                            @Override
                            public Observable<? extends String> call(Throwable throwable) {
                                System.out.println("error");
                                return Observable.just("error");
                            }
                        }).doOnEach(new Action1<Notification<? super String>>() {
            @Override
            public void call(Notification<? super String> notification) {
                System.out.println("doOnEach"+notification.toString());
            }
        }).toBlocking().toFuture();
    }
}
