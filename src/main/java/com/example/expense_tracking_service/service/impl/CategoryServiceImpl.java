package com.example.expense_tracking_service.service.impl;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.service.CategoryService;
import com.example.expense_tracking_service.service.exception.CategoryNotFoundException;
import com.example.expense_tracking_service.service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException(categoryId.toString());
        }
        return category.get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
