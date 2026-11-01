package com.ecommerce.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    @NotBlank(message = "Category can not be empty")
    private String name;
    private String description;
    private Long parentId;

}
