package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.ProductDto;
import entity.Category;
import entity.Product;

public class ProductMapper {
    public ProductDto toDto(Product product)
    {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .brand(product.getBrand())
                .discountedPrice(product.getDiscountPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategory().getId())
                .build();
    }
    public Product toEntity(ProductDto dto, Category category)
    {
            return Product.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .discountPrice(dto.getDiscountedPrice())
                    .quantity(dto.getQuantity())
                    .brand(dto.getBrand())
                    .imageUrl(dto.getImageUrl())
                    .category(category)
                    .build();
    }
}
