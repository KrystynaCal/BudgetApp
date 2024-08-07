package com.example.budget;

import com.example.budget.dto.CategoryCreateDto;
import com.example.budget.dto.CategoryDto;
import com.example.budget.model.CategoryType;
import com.example.budget.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceTest {

    private CategoryService categoryService;
    private InMemoryCategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository = new InMemoryCategoryRepository();
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void testSaveCategory() {
        //given
        CategoryCreateDto createDto = new CategoryCreateDto("Food", CategoryType.EXPENSE);
        CategoryDto savedCategory = categoryService.saveCategory(createDto);

        assertEquals("Food", savedCategory.name());
        assertEquals(CategoryType.EXPENSE, savedCategory.categoryType());
    }

    @Test
    public void testGetAllCategories() {
        CategoryCreateDto createDto = new CategoryCreateDto("Food", CategoryType.EXPENSE);
        categoryService.saveCategory(createDto);

        List<CategoryDto> categories = categoryService.getAllCategories(YearMonth.now());

        assertEquals(1, categories.size());
        assertEquals("Food", categories.get(0).name());
    }

    @Test
    public void testGetCategoryById() {
        CategoryCreateDto createDto = new CategoryCreateDto("Food", CategoryType.EXPENSE);
        CategoryDto savedCategory = categoryService.saveCategory(createDto);

        CategoryDto foundCategory = categoryService.getCategoryById(savedCategory.id());

        assertEquals(savedCategory.id(), foundCategory.id());
        assertEquals("Food", foundCategory.name());
    }
}
