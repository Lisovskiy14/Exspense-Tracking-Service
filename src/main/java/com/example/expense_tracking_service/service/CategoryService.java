package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(UUID categoryId);
    Category createCategory(Category category);
    void deleteCategoryById(UUID categoryId);
}
