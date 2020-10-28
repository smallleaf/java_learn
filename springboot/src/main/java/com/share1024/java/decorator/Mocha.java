package com.share1024.java.decorator;

/**
 * \* @Author: yesheng
 * \* Date: 2020/10/28 10:16
 * \* Description:
 * \
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() +",Mocha";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}