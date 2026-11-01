package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.entity.Inventory;
import com.ecommerce.inventoryservice.exception.InventoryNotFoundException;
import com.ecommerce.inventoryservice.mapper.InventoryMapper;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepo;
    private final InventoryMapper inventoryMapper;

    public InventoryResponse checkInventory(String skuCode)
    {
        Inventory inventory=inventoryRepo.findBySkuCode(skuCode)
                .orElseThrow(()->new InventoryNotFoundException(skuCode));
        return inventoryMapper.toResponse(inventory);

    }
    public void addInventory(InventoryRequest request)
    {
        Inventory inventory=inventoryMapper.toEntity(request);
        inventoryRepo.save(inventory);
    }
}
