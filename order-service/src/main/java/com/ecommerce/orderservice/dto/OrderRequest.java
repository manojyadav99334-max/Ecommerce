package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String skuCode;
    @Min(value = 1,message = "Quantity must be atleast 1")
    private Integer quantity;
    @NotNull(message = "price is mandatory")
    private BigDecimal price;

}
