package com.ecommerce.inventoryservice.mapper;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public Inventory toEntity(InventoryRequest request)
    {
        Inventory inventory=new Inventory();
        inventory.setQuantity(request.getQuantity());
        inventory.setSkuCode(request.getSkuCode());
        return inventory;
    }
    public InventoryResponse toResponse(Inventory inventory)
    {
        return new InventoryResponse(inventory.getSkuCode(),inventory.getQuantity()>0,inventory.getQuantity());
    }
}
