package com.example.budget.service;


import com.example.budget.model.Category;
import com.example.budget.model.CategoryCreateDto;
import com.example.budget.model.CategoryDto;
import com.example.budget.model.CategoryMapper;
import com.example.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Category> findById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category;
    }

    public Integer getTotalAmountForCategory(Long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            return category.getTotalAmount();
        } else {
            throw new IllegalArgumentException("Invalid category ID");
        }
    }
}
