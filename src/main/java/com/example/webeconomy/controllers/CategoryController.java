package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.services.CategoryService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    List<CategoryResponseDto> getCategorys(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    CategoryResponseDto getCategoryById(@PathVariable("id") Integer id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDto createCategory(@RequestBody CategoryUpdateDto dto){
        return this.categoryService.createCategory(dto);
    }
    
    @PutMapping("/{id}")
    CategoryResponseDto updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryUpdateDto dto){
        return this.categoryService.updateCategory(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory (@PathVariable("id") Integer id){
        return categoryService.deleteCategory(id);
    }
}
