package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.CategoryDto;
import com.ecommerce.productservice.mapper.CategoryMapper;
import com.ecommerce.productservice.repository.CategoryRepository;
import entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category category= categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepo.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category=categoryRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Category id is not found"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setParentId(dto.getParentId());
        return categoryMapper.toDto(categoryRepo.save(category));


    }

    @Override
    public void deleteCategory(Long id) {
            categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category=categoryRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Category id is not found"));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(categoryMapper::toDto).toList();
    }
}
