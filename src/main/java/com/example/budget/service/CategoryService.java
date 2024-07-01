package com.example.budget.service;


import com.example.budget.model.Category;
import com.example.budget.model.CategoryCreateDto;
import com.example.budget.model.CategoryDto;
import com.example.budget.model.CategoryMapper;
import com.example.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto saveCategory(CategoryCreateDto categoryCreateDto) {
        Category categoryEntity = Category.builder()
                .name(categoryCreateDto.name())
                .categoryType(categoryCreateDto.categoryType())
                .build();
        Category savedCategory = categoryRepository.save(categoryEntity);
        return CategoryMapper.toDTO(savedCategory);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoriesEntity = categoryRepository.findAll();
        List<CategoryDto> listCategoryDto = categoriesEntity
                .stream()
                .map(CategoryMapper::toDTO)
                .toList();
        return listCategoryDto;
    }
}
