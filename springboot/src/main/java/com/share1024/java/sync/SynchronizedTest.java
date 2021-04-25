package com.share1024.java.sync;

public class SynchronizedTest {
    private Object lock = new Object();

    public  void test(){
        synchronized (this){
            System.out.println("test");
        }
    }

    public synchronized void  test1(){
        System.out.println("test1");
    }

    public synchronized static void test2(){
        System.out.println("test2");
    }

    public void  test3(){
        synchronized (lock){
            System.out.println("test3");
        }
    }

    public static void main(String[] args) {
        SynchronizedTest  synchronizedTest = new SynchronizedTest();
        synchronizedTest.test();
    }
}
