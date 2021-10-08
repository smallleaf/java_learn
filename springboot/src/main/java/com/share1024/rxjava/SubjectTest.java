package com.share1024.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observers.Subscribers;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subjects.TestSubject;

/**
 *
 *
 * @author shengye
 * @Title
 * @Date 2021/9/8 16:16
 */
public class SubjectTest {

    @Test
    public void subject(){
        Observable observable = TestSubject.unsafeCreate(subscriber -> {
            subscriber.onNext("test");
        });
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Throwable");

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }
        });
    }


    @Test
    public void PublishSubject(){
        PublishSubject publishSubject = PublishSubject.create();
        publishSubject.onBackpressureBuffer()
                .doOnNext(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("====="+o);
                    }
                })
                .unsafeSubscribe(Subscribers.empty());
        publishSubject.onNext("111");
    }
}
