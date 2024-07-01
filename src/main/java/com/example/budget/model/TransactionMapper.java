package com.example.budget.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getAmount(), transaction.getName(), transaction.getDate(), transaction.getDescription(), transaction.getCategory().getName());
    }
}
