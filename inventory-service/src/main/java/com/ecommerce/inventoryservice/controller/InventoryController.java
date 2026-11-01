package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.InventoryRequest;
import com.ecommerce.inventoryservice.dto.InventoryResponse;
import com.ecommerce.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping("/{skuCode}")
    public ResponseEntity<InventoryResponse> checkInventory(@PathVariable String skuCode) {
        InventoryResponse response = inventoryService.checkInventory(skuCode);
        return ResponseEntity.ok(response);
    }

    // POST /api/inventory
    @PostMapping
    public ResponseEntity<String> addInventory(@RequestBody InventoryRequest request) {
        inventoryService.addInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inventory added successfully");
    }

}
