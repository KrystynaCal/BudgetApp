package com.example.budget.service;

import com.example.budget.model.TransactionCreateDto;
import com.example.budget.model.TransactionDTO;
import com.example.budget.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionDTO saveTransaction(TransactionCreateDto transactionCreateDto) {
        transactionRepository.save(transaction);
        return TransactionDTO;


    }
}
