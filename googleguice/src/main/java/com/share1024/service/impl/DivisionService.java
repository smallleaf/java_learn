package com.share1024.service.impl;

import com.share1024.service.ArithmeticService;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/26
 */
public class DivisionService implements ArithmeticService {
    @Override
    public void calculate(int a, int b) {
        System.out.println("Divisiton of " + a + " and " + b + " is: " + (a / b));
    }
}
