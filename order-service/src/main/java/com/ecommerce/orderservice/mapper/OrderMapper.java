package com.ecommerce.orderservice.mapper;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.entity.Order;

public class OrderMapper {
    public Order toEntity(OrderRequest request)
    {
        Order order=new Order();
        order.setSkuCode(request.getSkuCode());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        return order;
    }
    public OrderResponse toResponse(Order order)
    {
        return new OrderResponse(order.getOrderNumber(), order.getOrderStatus());
    }
}
