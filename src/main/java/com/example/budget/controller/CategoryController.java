package com.example.budget.controller;

import com.example.budget.dto.CategoryCreateDto;
import com.example.budget.dto.CategoryDto;
import com.example.budget.dto.TransactionDto;
import com.example.budget.service.CategoryService;
import com.example.budget.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final TransactionService transactionService;

    @GetMapping
    public String getAllCategories(Model model) {
        List<CategoryDto> categoriesList = categoryService.getAllCategories();
        System.out.println("Fetched Categories: " + categoriesList);
        model.addAttribute("categoriesList", categoriesList);
        return "home";
    }

    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("categoryCreateDto", new CategoryCreateDto(null, null));
        return "create-category";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryCreateDto categoryCreateDto, Model model) {
        model.addAttribute("categoryCreateDto", categoryCreateDto);
        categoryService.saveCategory(categoryCreateDto);
        return "redirect:/categories";
    }

    @GetMapping("/{categoryId}")
    public String viewCategory(@PathVariable Long categoryId, Model model) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        List<TransactionDto> transactions = transactionService.findByCategoryId(categoryId);

        model.addAttribute("category", categoryDto);
        model.addAttribute("transactions", transactions);
        return "view-transactions-for-category";
    }


}
