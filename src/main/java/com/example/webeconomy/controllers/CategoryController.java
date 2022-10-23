package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.services.CategoryService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService CategoryService;
    
    @Autowired
    CategoryController (CategoryService CategoryService){
        this.CategoryService = CategoryService;
    }

    @GetMapping
    List<Category> getCategorys(){
        return CategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable("id") Integer id){
        return CategoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDto createCategory(@RequestBody CategoryUpdateDto dto){
        return this.CategoryService.createCategory(dto);
    }
    
    @PutMapping("/{id}")
    CategoryResponseDto updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryUpdateDto dto){
        return this.CategoryService.updateCategory(id,dto);
    }
}
