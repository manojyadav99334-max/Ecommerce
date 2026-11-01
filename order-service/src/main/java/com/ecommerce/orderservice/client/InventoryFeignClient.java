package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryFeignClient {
    @GetMapping("/api/inventory/{skuCode}")
    InventoryResponse isInStock(@PathVariable String skuCode);
}
