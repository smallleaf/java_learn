package com.share1024.demo.helloworld;


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