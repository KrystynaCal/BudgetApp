package com.example.budget.controller;

import com.example.budget.model.TransactionCreateDto;
import com.example.budget.model.TransactionDto;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionCreateDto transactionCreateDto, @RequestParam Long categoryId) {
        return transactionService.saveTransaction(transactionCreateDto, categoryId);
    }

    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/category/{categoryId}")
    public List<TransactionDto> getAllTransactionByCategory(@PathVariable Long categoryId) {
        return transactionService.findByCategoryId(categoryId);
    }
}
