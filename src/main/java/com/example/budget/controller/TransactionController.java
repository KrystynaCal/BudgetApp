package com.example.budget.controller;

import com.example.budget.dto.TransactionCreateDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto, @RequestParam Long categoryId) {
        TransactionDto transactionDto = transactionService.saveTransaction(transactionCreateDto, categoryId);
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/transactions/category/{categoryId}")
                .buildAndExpand(categoryId);
        return ResponseEntity.created(uriComponents.toUri())
                .body(transactionDto);
    }

    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<TransactionDto> getAllTransactionByCategory(@PathVariable Long categoryId) {
        return transactionService.findByCategoryId(categoryId);
    }
}
