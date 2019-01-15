package com.share1024.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.share1024.api.IOrderService;
import com.share1024.model.Order;
import com.share1024.request.OrderRequest;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/14
 */
@Service(version = "v1",validation = "true",token = "123456")
public class OrderServiceImpl implements IOrderService {


    @Override
    public Order getOrderById(String orderId) {
        Order order = new Order();
        order.setAddress("address");
        order.setOrderId(orderId);
        order.setName("haha--");
        order.setPrice(12211F);
        order.setRemark(RpcContext.getContext().getAttachment("remarks"));
        return order;
    }

    @Override
    public Order getOrderBy(OrderRequest request) {
        Order order = new Order();
        order.setAddress("v1");
        order.setPrice(request.getPrice());
        order.setName("v1");
        order.setOrderId(request.getOrderId());
        return order;
    }
}
