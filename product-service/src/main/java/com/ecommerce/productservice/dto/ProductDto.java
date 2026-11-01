package com.ecommerce.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message = "product name is required")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @Positive(message = "price must be positive")
    private double price;
    private double discountedPrice;
    @Min(value = 1,message = "quantity must be 1 or more")
    private int quantity;
    private String brand;
    private String imageUrl;
    @NotNull(message = "category is required")
    private Long categoryId;

}
