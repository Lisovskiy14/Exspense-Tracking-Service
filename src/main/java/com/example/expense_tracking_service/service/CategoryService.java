package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    public Category saveCategory(Category category) {
        category.setId(UUID.randomUUID());
        return categoryRepository.saveCategory(category);
    }

    public void deleteCategoryById(UUID categoryId) {
        categoryRepository.deleteCategoryById(categoryId);
    }
}
