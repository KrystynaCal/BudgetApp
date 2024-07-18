package com.example.budget.service;

import com.example.budget.model.Category;
import com.example.budget.model.Transaction;
import com.example.budget.dto.TransactionCreateDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.mapper.TransactionMapper;
import com.example.budget.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;

    public TransactionDto saveTransaction(TransactionCreateDto transactionCreateDto, Long categoryId) {
        Optional<Category> categoryOpt = categoryService.findById(categoryId);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            Transaction transactionEntity = Transaction.builder()
                    .amount(transactionCreateDto.amount())
                    .name(transactionCreateDto.name())
                    .date(transactionCreateDto.date())
                    .category(category)
                    .description(transactionCreateDto.description())
                    .build();
            Transaction savedTransaction = transactionRepository.save(transactionEntity);
            return TransactionMapper.toDto(savedTransaction);
        }
        else {
            throw new IllegalArgumentException("InvalidcategoryID");
        }


    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactionsEntity = transactionRepository.findAll();
        List<TransactionDto> listTransactionDto =  transactionsEntity
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
        return listTransactionDto;
    }

    public List<TransactionDto> findByCategoryId(Long categoryId) {
        List<Transaction> transactionsEntityByCategoryId = transactionRepository.findByCategoryId(categoryId);
        List<TransactionDto> listTransactionDtoByCategoryId = transactionsEntityByCategoryId
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
        return listTransactionDtoByCategoryId;
    }

    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }

    public List<TransactionDto> findByCategoryIdAndMonth(Long categoryId, YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        List<Transaction> transactionsEntityByCategoryIdAndMonth = transactionRepository.findByCategoryIdAndMonth(categoryId, startDate, endDate);
        return transactionsEntityByCategoryIdAndMonth.stream()
                .map(TransactionMapper::toDto)
                .toList();
    }
}
