package com.example.budget.repository;

import com.example.budget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCreatedAt(YearMonth yearMonth);
}
