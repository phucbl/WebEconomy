package com.example.webeconomy.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;


public interface CategoryService {
    public List<CategoryResponseDto> getAllCategories();
    public CategoryResponseDto getCategoryById(Integer id);
    public CategoryResponseDto createCategory(CategoryUpdateDto dto);
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateDto dto);
    public ResponseEntity<ResponseDto> deleteCategory (Integer id);
}
