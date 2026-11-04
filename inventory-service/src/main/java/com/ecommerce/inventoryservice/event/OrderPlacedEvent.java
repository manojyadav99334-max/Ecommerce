package com.ecommerce.inventoryservice.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderPlacedEvent {
    private String eventId;
    private String orderId;
    private String skucode;
    private Integer quantity;
    private LocalDateTime eventTime;

}
