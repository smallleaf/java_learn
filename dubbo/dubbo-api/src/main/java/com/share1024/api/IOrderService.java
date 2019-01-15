package com.share1024.api;

import com.share1024.model.Order;
import com.share1024.request.OrderRequest;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/14
 */
public interface IOrderService {

    Order getOrderById(String orderId);

    Order getOrderBy(OrderRequest request);
}
