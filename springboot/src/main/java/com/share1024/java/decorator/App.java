package com.share1024.java.decorator;

/**
 * \* @Author: yesheng
 * \* Date: 2020/10/28 10:18
 * \* Description:
 * \
 */
public class App {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() +","+beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        System.out.println(beverage1.getDescription() +","+beverage1.cost());

    }
}