package com.ecommerce.inventoryservice.consumer;

import com.ecommerce.inventoryservice.event.OrderPlacedEvent;
import com.ecommerce.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-event",groupId = "inventory-group")
    public void consume(OrderPlacedEvent event)
    {
        inventoryService.updateStock(event);
    }
}
