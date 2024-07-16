package com.example.budget.controller;

import com.example.budget.dto.CategoryCreateDto;
import com.example.budget.dto.CategoryDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.service.CategoryService;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;

    @GetMapping
    public String getAllCategories(@RequestParam(value = "yearMonth", required = false)
                                       @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth, Model model) {
        if(yearMonth == null){
            yearMonth = YearMonth.now();
        }
        List<CategoryDto> categoriesList = categoryService.getAllCategories(yearMonth);
        System.out.println("Fetched Categories: " + categoriesList);
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("selectedMonth", yearMonth);
        return "home";
    }

    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("categoryCreateDto", new CategoryCreateDto(null, null));
        return "create-category";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryCreateDto categoryCreateDto, Model model) {
        categoryService.saveCategory(categoryCreateDto);
        System.out.println(categoryCreateDto);
        return "redirect:/categories";
    }

    @GetMapping("/{categoryId}")
    public String viewCategory(@PathVariable Long categoryId, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth, Model model) {
        if (yearMonth == null) {
            yearMonth = YearMonth.now();
        }
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        List<TransactionDto> transactions = transactionService.findByCategoryIdAndMonth(categoryId, yearMonth);


        model.addAttribute("category", categoryDto);
        model.addAttribute("transactions", transactions);
        model.addAttribute("selectedMonth", yearMonth);
        return "view-transactions-for-category";
    }


}
