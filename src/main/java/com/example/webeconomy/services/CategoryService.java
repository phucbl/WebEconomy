package com.example.webeconomy.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;


public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Integer id);
    public CategoryResponseDto createCategory(CategoryUpdateDto dto);
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateDto dto);
    public ResponseEntity<ResponseDto> deleteCategory (Integer id);
}
