package com.example.expense_tracking_service.service.repository.impl;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.service.exception.CategoryNotFoundException;
import com.example.expense_tracking_service.service.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {
    private final Map<UUID, Category> categories = new HashMap<>();

    {
        UUID id1 = UUID.randomUUID();
        categories.put(id1, Category.builder()
                .id(id1)
                .name("Food")
                .build());
        UUID id2 = UUID.randomUUID();
        categories.put(id2, Category.builder()
                .id(id2)
                .name("Transport")
                .build());
        UUID id3 = UUID.randomUUID();
        categories.put(id3, Category.builder()
                .id(id3)
                .name("Utilities")
                .build());
    }

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

    @Override
    public List<Category> getAllCategories() {
        return List.copyOf(categories.values());
    }
}
