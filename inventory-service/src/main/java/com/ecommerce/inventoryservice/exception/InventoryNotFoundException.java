package com.ecommerce.inventoryservice.exception;


public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(String skuCode)
    {
        super("Inventory Not Found: "+skuCode);
    }
}
