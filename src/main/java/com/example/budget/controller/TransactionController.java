package com.example.budget.controller;

import com.example.budget.model.TransactionCreateDto;
import com.example.budget.model.TransactionDTO;
import com.example.budget.service.TransactionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    TransactionService transactionService;

    public TransactionDTO createTransaction(@RequestBody TransactionCreateDto transactionCreateDto){
        transactionService.saveTransaction(transactionCreateDto);
        return null;
    }
}
