package com.example.expense_tracking_service.service.repository.impl;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.service.exception.CategoryNotFoundException;
import com.example.expense_tracking_service.service.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {
    private final Map<UUID, Category> categories = new HashMap<>();

    @Override
    public Category getCategoryById(UUID id) {
        if (!categories.containsKey(id)) {
            throw new CategoryNotFoundException(id.toString());
        }
        return categories.get(id);
    }

    @Override
    public Category saveCategory(Category category) {
        categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteCategoryById(UUID id) {
        categories.remove(id);
    }
}
