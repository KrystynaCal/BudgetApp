package com.example.budget.service;

import com.example.budget.model.Transaction;
import com.example.budget.model.TransactionCreateDto;
import com.example.budget.model.TransactionDto;
import com.example.budget.model.TransactionMapper;
import com.example.budget.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionDto saveTransaction(TransactionCreateDto transactionCreateDto) {
        Transaction transactionEntity = Transaction.builder()
                .amount(transactionCreateDto.amount())
                .name(transactionCreateDto.name())
                .date(transactionCreateDto.date())
                .description(transactionCreateDto.description())
                .build();

        Transaction savedTransaction = transactionRepository.save(transactionEntity);
        return TransactionMapper.toDto(savedTransaction);
    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactionsEntity = transactionRepository.findAll();
        List<TransactionDto> listTransactionDto =  transactionsEntity
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
        return listTransactionDto;
    }
}
