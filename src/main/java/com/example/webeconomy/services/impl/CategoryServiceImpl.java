package com.example.webeconomy.services.impl;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.CategoryService;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
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
        Optional<Category> categoryOptional = this.categoryRepository.findByName(dto.getName());
        if (!categoryOptional.isEmpty()){
            throw new ItemExistException("Category was found, id is: "+ categoryOptional.get().getId());
        }
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

    @Override
    public ResponseEntity<ResponseDto> deleteCategory (Integer id){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }
        Category category = categoryOptional.get();
        List<Product> products = productRepository.findByCategoryId(category.getId());
        if (!products.isEmpty()) {
            throw new ValidationException("This category still has products");
        }
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Successfully!","200"));
    }

}
