package com.example.budget.dto;

import com.example.budget.model.CategoryType;

public record CategoryCreateDto(String name, CategoryType categoryType) {
}
