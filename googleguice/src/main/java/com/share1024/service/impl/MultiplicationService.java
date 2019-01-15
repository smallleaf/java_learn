package com.share1024.service.impl;

import com.share1024.service.ArithmeticService;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/26
 */
public class MultiplicationService implements ArithmeticService {
    @Override
    public void calculate(int a, int b) {
        System.out.println("Multiplication of " + a + " and " + b + " is: " + (a * b));
    }
}
