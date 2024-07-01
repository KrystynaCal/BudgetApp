package com.example.budget.model;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
 public class CategoryMapper {

    public static CategoryDto toDTO(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getCategoryType());
    }

}
