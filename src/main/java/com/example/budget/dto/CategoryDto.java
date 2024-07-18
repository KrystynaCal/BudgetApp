package com.example.budget.dto;

import com.example.budget.model.CategoryType;

import java.time.YearMonth;

public record CategoryDto(long id, String name, CategoryType categoryType, Integer totalAmount, YearMonth createdAt) {
}
