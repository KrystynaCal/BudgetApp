package com.example.budget.dto;

import com.example.budget.model.CategoryType;

public record CategoryDto(long id, String name, CategoryType categoryType) {
}
