package com.example.webeconomy.services.impl;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public CategoryServiceImpl( ProductRepository productRepository ,CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories(){
        List<Category> categories = this.categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Category List Is Empty.");
        }
        List<CategoryResponseDto> categoryResponseDtos = modelMapper.map(categories,new TypeToken<List<CategoryResponseDto>>() {}.getType());
        for (CategoryResponseDto categoryResponseDto : categoryResponseDtos) {
            categoryResponseDto
            .setNumberProduct(productRepository.findByCategoryId(categoryResponseDto.getId())
            .get()
            .size());
        }
        return categoryResponseDtos;
    }  
    @Override
    public CategoryResponseDto getCategoryById(Integer id){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found with Controller Advice");
        }
        CategoryResponseDto categoryrResponseDto =modelMapper.map(categoryOptional.get(),CategoryResponseDto.class) ;
        return categoryrResponseDto;

    }
    @Override
    public CategoryResponseDto createCategory(CategoryUpdateDto dto){
        Optional<Category> categoryOptional = this.categoryRepository.findByName(dto.getName());
        if (categoryOptional.isPresent()){
            throw new ItemExistException("Category was found, id is: "+ categoryOptional.get().getId());
        }
        Category category = modelMapper.map(dto,Category.class);
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(Integer id, CategoryUpdateDto dto){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }
        Category category = categoryOptional.get();
        category.setName(dto.getName());
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCategory (Integer id){
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);
        if (categoryOptional.isEmpty()){
            throw new ResourceNotFoundException("Category Not Found");
        }
        Category category = categoryOptional.get();
        Optional<List<Product>> products = productRepository.findByCategoryId(category.getId());
        if (products.get().size()>0) {
            throw new BadRequestException("This category still has products");
        }
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Successfully!","200"));
    }

}

