package com.example.budget.controller;

import com.example.budget.dto.CategoryCreateDto;
import com.example.budget.dto.CategoryDto;
import com.example.budget.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

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


    @GetMapping("/total-amount")
    public Integer getTotalAmount(@RequestParam Long categoryId) {
        return categoryService.getTotalAmountForCategory(categoryId);
    }

}
