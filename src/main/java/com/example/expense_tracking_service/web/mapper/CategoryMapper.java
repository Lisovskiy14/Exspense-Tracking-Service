package com.example.expense_tracking_service.web.mapper;

import com.example.expense_tracking_service.domain.Category;
import com.example.expense_tracking_service.dto.category.CategoryDto;
import com.example.expense_tracking_service.dto.category.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryRequest categoryRequest);
}
