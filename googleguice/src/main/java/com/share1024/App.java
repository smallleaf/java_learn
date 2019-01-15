package com.share1024;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        Injector injector = Guice.createInjector(new GuiceInjector());
        MyApplication app = injector.getInstance(MyApplication.class);
        app.calculate(1, 2);

    }
}
