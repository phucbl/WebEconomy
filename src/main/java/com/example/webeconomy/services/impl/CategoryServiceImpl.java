package com.example.webeconomy.services.impl;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.CategoryService;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    public CategoryServiceImpl( CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll();
    }  
    @Override
    public Category getCategoryById(Integer id){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found with Controller Advice");
        }
        Category category = categoryOptional.get();
        return category;

    }
    @Override
    public CategoryResponseDto createCategory(CategoryUpdateDto dto){
        Category category = modelMapper.map(dto,Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateDto dto){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }
        
        Category category = categoryOptional.get();
        modelMapper.map(dto,category);
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

}
