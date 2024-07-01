package com.example.budget.model;

import java.time.LocalDate;

public record TransactionDto(long id, Integer amount, String name, LocalDate date, String description, String categoryName) {
}
