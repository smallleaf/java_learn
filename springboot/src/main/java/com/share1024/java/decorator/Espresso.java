package com.share1024.java.decorator;

/**
 * \* @Author: yesheng
 * \* Date: 2020/10/28 10:14
 * \* Description:
 * \
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "Espresso";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}