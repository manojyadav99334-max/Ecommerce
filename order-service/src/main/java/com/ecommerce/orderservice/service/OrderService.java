package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);

}
