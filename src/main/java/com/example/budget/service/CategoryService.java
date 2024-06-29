package com.example.budget.service;


import com.example.budget.model.Category;
import com.example.budget.model.CategoryCreateDto;
import com.example.budget.model.CategoryDto;
import com.example.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryDto saveCategory(CategoryCreateDto categoryCreateDto) {
        Category category = Category.builder()
                .name(categoryCreateDto.name())
                .type(categoryCreateDto.type())
                .build();
        Category savedCategory = categoryRepository.save(category);
        return new CategoryDto(savedCategory.getId(), savedCategory.getName(), savedCategory.getType());
    }
}
