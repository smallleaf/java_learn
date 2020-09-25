package com.share1024.demo.helloworld;

import java.util.Date;

@FunctionalInterface
public interface DateLambda {

    String format(Date date,String format);
}
