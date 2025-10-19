package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.dto.category.CategoryDto;
import com.example.expense_tracking_service.dto.category.CategoryListDto;
import com.example.expense_tracking_service.dto.category.CategoryRequest;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryRequest categoryRequest);
    CategoryListDto toCategoryListDto(Collection<Category> categories);
}
