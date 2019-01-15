package com.share1024.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/14
 */
@Data
public class Order implements Serializable{

    private String orderId;

    private String name;

    private Float price;

    private String address;

    private String remark;

}
