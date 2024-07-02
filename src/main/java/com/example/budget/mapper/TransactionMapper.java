package com.example.budget.mapper;

import com.example.budget.model.Transaction;
import com.example.budget.dto.TransactionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getAmount(), transaction.getName(), transaction.getDate(), transaction.getDescription(), transaction.getCategory().getId(), transaction.getCategory().getName());
    }
}
