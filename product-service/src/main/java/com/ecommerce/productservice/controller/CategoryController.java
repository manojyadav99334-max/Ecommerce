package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.CategoryDto;
import com.ecommerce.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Create category
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // Get single category
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
