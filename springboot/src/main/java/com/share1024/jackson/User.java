package com.share1024.jackson;

import java.util.Date;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public class User {

    private String username;

    private String bigHouse;

    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBigHouse() {
        return bigHouse;
    }

    public void setBigHouse(String bigHouse) {
        this.bigHouse = bigHouse;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", bigHouse='" + bigHouse + '\'' +
                '}';
    }
}
