package com.share1024.demo.helloworld;

/**
 * \* @Author: yesheng
 * \* Date: 2020/9/25 12:27
 * \* Description:
 * \
 */
public class MyData {

    private String userName;
    private int age;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}