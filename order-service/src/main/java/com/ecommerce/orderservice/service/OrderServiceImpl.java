package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.Repository.OrderRepository;
import com.ecommerce.orderservice.client.InventoryFeignClient;
import com.ecommerce.orderservice.dto.InventoryResponse;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.event.OrderPlacedEvent;
import com.ecommerce.orderservice.kafka.OrderEventProducer;
import com.ecommerce.orderservice.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventProducer orderEventProducer;
    private final InventoryFeignClient inventoryFeignClient;
    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        InventoryResponse inventoryResponse=inventoryFeignClient.isInStock(request.getSkuCode());
        if(!inventoryResponse.isInStock())
        {
            throw new RuntimeException("product is out of stock");
        }
        Order order=orderMapper.toEntity(request);
        String orderId=UUID.randomUUID().toString();
        order.setOrderNumber(orderId);
        order.setOrderStatus("CREATED");
        orderRepository.save(order);
        OrderPlacedEvent event=new OrderPlacedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setOrderId(orderId);
        event.setSkucode(request.getSkuCode());
        event.setQuantity(request.getQuantity());
        event.setEventTime(LocalDateTime.now());
        orderEventProducer.sendOrderEvent(event);

        return orderMapper.toResponse(order);
    }
}
