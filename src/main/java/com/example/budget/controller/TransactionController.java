package com.example.budget.controller;

import com.example.budget.dto.TransactionCreateDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/create")
    public String showCreateForm(@RequestParam Long categoryId, Model model) {
        model.addAttribute("categoryId", categoryId);
        return "create-transactions"; //
    }

    @PostMapping("/create")
    public String createTransaction(@ModelAttribute TransactionCreateDto transactionCreateDto, @RequestParam Long categoryId, Model model) {
        TransactionDto transactionDto = transactionService.saveTransaction(transactionCreateDto, categoryId);
        model.addAttribute("categoryId", categoryId);
        return "redirect:/categories/" + categoryId;
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
