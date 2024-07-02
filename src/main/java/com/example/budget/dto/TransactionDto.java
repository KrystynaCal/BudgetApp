package com.example.budget.dto;

import java.time.LocalDate;

public record TransactionDto(long id, Integer amount, String name, LocalDate date, String description,long categoryId, String categoryName) {
}
