package com.share1024.java.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-12-28
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyAppModule());
        BillService billService = injector.getInstance(BillService.class);
        billService.bill();
    }
}
