package com.share1024.threadpool;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/12
 */
public class ProductThread implements Runnable {
    @Override
    public void run() {
        System.out.println("生成产品了"+Thread.currentThread().getName());
    }
}
