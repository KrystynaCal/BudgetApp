package com.example.budget.controller;

import com.example.budget.dto.CategoryCreateDto;
import com.example.budget.dto.CategoryDetailsDto;
import com.example.budget.dto.CategoryDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.model.CategoryType;
import com.example.budget.service.CategoryService;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private  final TransactionService transactionService;

    @GetMapping
    public List<CategoryDto> getAllCategories(@RequestParam(value = "yearMonth", required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {

        if (yearMonth == null) {
            yearMonth = YearMonth.now();
        }

        int totalExpense = categoryService.getTotalAmountByTypeAndMonth(CategoryType.EXPENSE, yearMonth);
        int totalIncome = categoryService.getTotalAmountByTypeAndMonth(CategoryType.INCOME, yearMonth);
        int totalSaved = totalIncome - totalExpense;

        return categoryService.getAllCategories(yearMonth);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.saveCategory(categoryCreateDto);
    }

    @GetMapping("/{categoryId}")
    public CategoryDetailsDto viewCategory(@PathVariable Long categoryId, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        if (yearMonth == null) {
            yearMonth = YearMonth.now();
        }
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        List<TransactionDto> transactions = transactionService.findByCategoryIdAndMonth(categoryId, yearMonth);

        return new CategoryDetailsDto(categoryDto, transactions);
    }
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

}
