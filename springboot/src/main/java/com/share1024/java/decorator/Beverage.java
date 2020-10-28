package com.share1024.java.decorator;

/**
 * \* @Author: yesheng
 * \* Date: 2020/10/28 10:09
 * \* Description:
 * \
 */
public abstract class Beverage {

    String description ="";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}