package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.service.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CategoryService {
    private final Map<UUID, Category> categories = new HashMap<>();

    public Category getCategoryById(UUID categoryId) {
        if (!categories.containsKey(categoryId)) {
            throw new CategoryNotFoundException(categoryId.toString());
        }
        return categories.get(categoryId);
    }

    public Category saveCategory(Category category) {
        category.setId(UUID.randomUUID());
        categories.put(category.getId(), category);
        return category;
    }

    public void deleteCategoryById(UUID categoryId) {
        categories.remove(categoryId);
    }
}
