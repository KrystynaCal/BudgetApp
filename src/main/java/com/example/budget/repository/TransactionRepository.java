package com.example.budget.repository;

import com.example.budget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategoryId(Long categoryId);

    @Query("SELECT t FROM Transaction t WHERE t.category.id = :categoryId AND t.date >= :startDate AND t.date <= :endDate")
    List<Transaction> findByCategoryIdAndMonth(@Param("categoryId") Long categoryId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
