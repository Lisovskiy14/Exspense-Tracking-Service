package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.dto.category.CategoryRequest;
import com.example.expense_tracking_service.service.CategoryService;
import com.example.expense_tracking_service.web.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryMapper.toCategoryDto(
                        categoryService.getCategoryById(categoryId)));
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryMapper.toCategoryDto(
                        categoryService.saveCategory(
                                categoryMapper.toCategory(categoryRequest))));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable UUID categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryMapper.toCategoryListDto(
                        categoryService.getAllCategories()));
    }
}
