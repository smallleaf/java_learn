package com.share1024.rxjava;

import com.google.inject.internal.cglib.core.$MethodWrapper;
import com.netflix.hystrix.metric.HystrixCommandCompletion;
import org.junit.After;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author shengye
 * @Title
 * @Date 2021/9/8 16:23
 */
public class ObservableTest {


    @Test
    public void create(){
        Observable<Object> observable = Observable.create(subscriber -> {
            subscriber.onNext("yesheng");
            throw new NullPointerException();
        });
        Subscriber<Object> subscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:"+e.getMessage());

            }

            @Override
            public void onNext(Object s) {
                System.out.println("onNext:"+s);
            }
        };

        observable.subscribe(subscriber);
    }


    @Test
    public void interval() throws IOException {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }
        });
        System.in.read();
    }

    @Test
    public void just(){
        Observable<String> observable = Observable.just("yesheng");
        observable.subscribe(new Subscriber<String>() {
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

    @Test
    public void range(){
        Observable<Integer>  observable = Observable.range(0,10);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    @Test
    public void from(){
        Observable<String> observable =   Observable.from(Arrays.asList("test","test2"));
        observable.subscribe(new Subscriber<String>() {
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


    @Test
    public void defer(){
        Observable<String> observable =  Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                System.out.println("call");
                return Observable.defer(new Func0<Observable<String>>() {
                    @Override
                    public Observable<String> call() {
                        System.out.println("call2");
                        return Observable.from(Arrays.asList("yesheng","yesheng2"));
                    }
                });
            }
        });

        observable.subscribe(new Subscriber<String>() {
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

    @Test
    public void empty(){
        Observable observable = Observable.never();
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }
        });
    }


    @Test
    public  void map(){
        Observable observable = Observable.just("1").map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                return s+"test";
            }
        });
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }
        });

    }


    @Test
    public void flatMap(){
        Observable.just(1,2,3,4,5).flatMap(t->Observable.just(t+2))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    @Test
    public void buffer(){
        Observable.just(1,2,3,4,5,6,7,8).buffer(3).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                System.out.println("=="+integers);
            }
        });
    }


    @Test
    public void reduce(){
        Observable.just(1,2,3,4,5,6).reduce(new int[5], new Func2<int[], Integer, int[]>() {
            @Override
            public int[] call(int[] ints, Integer integer) {
                ints[0] += integer;
                return ints;
            }
        }).subscribe(new Subscriber<int[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(int[] ints) {
                System.out.println("==="+ints[0]);
            }
        });

        Observable.just(1,2,3,4,5,6).reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void scan(){
        Observable.just(1,2,3,4,5,6).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void window(){
        Observable.just(1,2,3,4,5,6,7,8,9,10).window(5,1).subscribe(new Subscriber<Observable<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Observable<Integer> integerObservable) {
                System.out.println("====");
                integerObservable.subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }
                });
            }
        });
    }


    /**
     * 每隔 10s计算一组数据，例如得出 500，200，100
     * 那么 windows(20,1) 就是以每一步来计算记录。例如 500,（700,200）,(800,300,100)
     *
     * @throws IOException
     */
    @Test
    public void windowTime2() throws IOException {
        Subject<Long,Long> writeOnlySubject = new SerializedSubject<Long, Long>(PublishSubject.<Long>create());
        Observable readOnlyStream = writeOnlySubject.share();
        Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return readOnlyStream
                        .window(10,TimeUnit.SECONDS).flatMap(new Func1<Observable<Long>, Observable<Long>>() {
                            @Override
                            public Observable<Long> call(Observable<Long> longObservable) {
                                return longObservable.reduce(0L, new Func2<Long, Long, Long>() {
                                    @Override
                                    public Long call(Long r, Long aLong) {
                                        System.out.println(r+aLong);
                                        return r+aLong;
                                    }
                                });
                            }
                        });
            }
        }).window(20,1)
        .flatMap(new Func1<Observable<Long>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<Long> longObservable) {
                return longObservable.scan(0L, new Func2<Long, Long, Long>() {
                    @Override
                    public Long call(Long integer, Long aLong) {
                        System.out.println("=="+(integer + aLong));
                        return integer + aLong;
                    }
                });
            }
        }).unsafeSubscribe(Subscribers.empty());
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->{
            Random random = new Random();
            int result = random.nextInt(100);
            System.out.println("gen "+result);
            writeOnlySubject.onNext((long)result);
        },0,2,TimeUnit.SECONDS);
        System.in.read();
    }


    @Test
    public void windowTime() throws IOException {


        Observable.defer(new Func0<Observable<int[]>>() {
            @Override
            public Observable<int[]> call() {
                return  Observable.interval(100,TimeUnit.MILLISECONDS).take(50)
                        .window(1000,TimeUnit.MILLISECONDS)
                        .flatMap(new Func1<Observable<Long>, Observable<int[]>>() {
                            @Override
                            public Observable<int[]> call(Observable<Long> longObservable) {
                                int[] ints = new int[10];
                                return longObservable.reduce(ints, new Func2<int[], Long, int[]>() {
                                    @Override
                                    public int[] call(int[] ints, Long aLong) {
                                        ints[0] += aLong;
                                        return ints;
                                    }
                                });
                            }
                        });
            }
        }).window(50)
        .flatMap(new Func1<Observable<int[]>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<int[]> observable) {
                int[] ints = new int[10];
                return observable.scan(ints, new Func2<int[], int[], int[]>() {
                    @Override
                    public int[] call(int[] ints, int[] ints2) {
                        ints[0] += ints2[0];
                        return ints;
                    }
                });
            }
        }).unsafeSubscribe(Subscribers.empty());
        ;
        System.in.read();
    }

}
