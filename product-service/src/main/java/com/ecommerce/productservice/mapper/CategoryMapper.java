package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.CategoryDto;
import entity.Category;

public class CategoryMapper {
    public CategoryDto toDto(Category category)
    {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParentId())
                .build();
    }
    public Category toEntity(CategoryDto dto)
    {
        return Category.builder()
                .parentId(dto.getParentId())
                .description(dto.getDescription())
                .name(dto.getName())
                .build();

    }
}
