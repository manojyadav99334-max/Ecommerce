package com.ecommerce.inventoryservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String skuCode;
    private boolean inStock;
    private Integer availableQuantity;

}
