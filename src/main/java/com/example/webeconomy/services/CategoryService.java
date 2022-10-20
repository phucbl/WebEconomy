package com.example.webeconomy.services;

import java.util.List;

import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;


public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Integer id);
    public CategoryResponseDto createCategory(CategoryUpdateDto dto);
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateDto dto);
}
