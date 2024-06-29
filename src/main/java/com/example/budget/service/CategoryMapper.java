package com.example.budget.service;


import com.example.budget.model.Category;
import com.example.budget.model.CategoryDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
 class CategoryMapper {

    static CategoryDto toDTO(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getType());
    }

}
