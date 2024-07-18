package com.example.budget.dto;

import java.util.List;

public class CategoryDetailsDto {
    private final CategoryDto category;
    private final List<TransactionDto> transactions;

    public CategoryDetailsDto(CategoryDto category, List<TransactionDto> transactions) {
        this.category = category;
        this.transactions = transactions;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }
}
