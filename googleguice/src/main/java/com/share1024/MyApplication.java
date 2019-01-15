package com.share1024;

import com.google.inject.Inject;
import com.share1024.service.ArithmeticService;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/26
 */
public class MyApplication {
    private ArithmeticService service;

    @Inject
    public void setService(ArithmeticService service) {
        this.service = service;
    }

    public void calculate(int a, int b) {
        service.calculate(a, b);
    }

}
