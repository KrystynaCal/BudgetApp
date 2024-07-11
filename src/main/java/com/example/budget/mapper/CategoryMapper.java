package com.example.budget.mapper;


import com.example.budget.model.Category;
import com.example.budget.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
 public class CategoryMapper {

    public static CategoryDto toDTO(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getCategoryType(), category.getTotalAmount());
    }

}
