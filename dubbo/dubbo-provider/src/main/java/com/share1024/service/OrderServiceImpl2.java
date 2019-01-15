package com.share1024.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.share1024.api.IOrderService;
import com.share1024.model.Order;
import com.share1024.request.OrderRequest;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/14
 */
@Service(version = "v2")
public class OrderServiceImpl2 implements IOrderService {


    @Override
    public Order getOrderById(String orderId) {
        Order order = new Order();
        order.setAddress("address-v2");
        order.setOrderId(orderId);
        order.setName("haha--");
        order.setPrice(12211F);
        return order;
    }

    @Override
    public Order getOrderBy(OrderRequest request) {
        Order order = new Order();
        order.setAddress("v2");
        order.setPrice(request.getPrice());
        order.setName("v2");
        order.setOrderId(request.getOrderId());
        return order;
    }
}
