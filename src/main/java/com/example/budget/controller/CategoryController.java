package com.example.budget.controller;

import com.example.budget.model.Category;
import com.example.budget.model.CategoryCreateDto;
import com.example.budget.model.CategoryDto;
import com.example.budget.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.saveCategory(categoryCreateDto);
    }
    @GetMapping
    List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
