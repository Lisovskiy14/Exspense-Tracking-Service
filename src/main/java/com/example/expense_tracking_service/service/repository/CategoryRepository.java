package com.example.expense_tracking_service.service.repository;

import com.example.expense_tracking_service.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository {
    Category getCategoryById(UUID id);
    Category saveCategory(Category category);
    void deleteCategoryById(UUID id);
    List<Category> getAllCategories();
}
