package com.springbootblog.service;

import com.springbootblog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long categoryId);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteById(Long categoryId);
}
