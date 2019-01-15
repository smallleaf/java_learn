package com.share1024.java;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-13
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception {
        Integer i = 0;
        Thread product = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (i){
                    if(i == 0){
                        try {
                            i.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread cosumer = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (i){
                    if(i != 0){
                        try {
                            i.notify();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        for(int b=0;b<1000;b++){
            System.out.println(b);
            new Thread(new SynAddRunnable(1,2)).start();
            new Thread(new SynAddRunnable(2,1)).start();
        }


        System.in.read();
    }


    static class SynAddRunnable implements Runnable{

        int a,b;

        public SynAddRunnable(int a,int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                }
            }
        }
    }


}
