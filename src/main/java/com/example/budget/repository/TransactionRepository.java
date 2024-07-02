package com.example.budget.repository;

import com.example.budget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategoryId(Long categoryId);
}
