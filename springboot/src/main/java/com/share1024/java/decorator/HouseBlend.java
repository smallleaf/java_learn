package com.share1024.java.decorator;

/**
 * \* @Author: yesheng
 * \* Date: 2020/10/28 10:15
 * \* Description:
 * \
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}