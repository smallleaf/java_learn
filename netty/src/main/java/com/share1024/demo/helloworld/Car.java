package com.share1024.demo.helloworld;

import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.*;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/21 21:47
 * \* Description:
 * \
 */
public class Car {
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void bugTest(int data){
        System.out.println(data);
    }
    public static void main(String[] args) {
        Car car = new Car();
        car.bugTest(car.getId());


    }
}