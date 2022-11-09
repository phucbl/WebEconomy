package com.example.webeconomy.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.data.entities.Product;
import com.example.webeconomy.data.repositories.CategoryRepository;
import com.example.webeconomy.data.repositories.ProductRepository;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.services.impl.*;
import com.example.webeconomy.exceptions.*;

@Configuration
@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {

    CategoryServiceImpl categoryServiceImpl;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProductRepository productRepository;
    Product product;
    ModelMapper modelMapper;
    Category category;
    CategoryUpdateDto categoryUpdateDto;
    CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        category = mock(Category.class);
        product = mock(Product.class);
        modelMapper = mock(ModelMapper.class);
        categoryUpdateDto = mock(CategoryUpdateDto.class);
        categoryResponseDto = mock(CategoryResponseDto.class);
        categoryServiceImpl = new CategoryServiceImpl(productRepository, categoryRepository, modelMapper);
    }

    @Test
    void getAllCategories_WhenAccountEmpty_ShouldThrowResourceNotFoundException() {
        List<Category> categories = new ArrayList();
        when(categoryRepository.findAll()).thenReturn(categories);
        ResourceNotFoundException actual = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImpl.getAllCategories());
        assertThat("Category List Is Empty.", is(actual.getMessage()));
    }

    @Test
    void getAllCategories_WhenCategoryValid_ShouldThrowResourceNotFoundException() {
        List<Category> categories = new ArrayList();
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList();
        List<Product> products = new ArrayList();
        products.add(product);
        categories.add(category);
        categoryResponseDtos.add(categoryResponseDto);
        when(categoryRepository.findAll()).thenReturn(categories);
        when(modelMapper.map(categories, new TypeToken<List<CategoryResponseDto>>() {
        }.getType())).thenReturn(categoryResponseDtos);
        when(productRepository.findByCategoryId(categoryResponseDto.getId())).thenReturn(Optional.of(products));
        List<CategoryResponseDto> actual = categoryServiceImpl.getAllCategories();
        assertThat(categoryResponseDtos, is(actual));
    }

    @Test
    void getCategoryById_WhenCategoryNull_ShouldThrowResourseNotFoundException() {
        Category category = mock(Category.class);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());

        ResourceNotFoundException expected = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImpl.getCategoryById(category.getId()));

        assertThat("Category Not Found with Controller Advice", is(expected.getMessage()));

    }

    @Test
    void getCategoryById_WhenCategoryNotNull_ShouldReturnCategoryObject() {
        Category category = new Category();
        category.setId(1);
        CategoryResponseDto expected = mock(CategoryResponseDto.class);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryResponseDto.class)).thenReturn(expected);
        CategoryResponseDto actual = categoryServiceImpl.getCategoryById(1);

        assertThat(actual, is(expected));
    }

    @Test
    void createCategory_WhenCategoryExisted_ShouldThrowItemExistException() {
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
        ItemExistException actual = Assertions.assertThrows(ItemExistException.class,
                () -> categoryServiceImpl.createCategory(categoryUpdateDto));
        assertEquals("Category was found, id is: "+ Optional.of(category).get().getId(), actual.getMessage());
    }

    @Test
    void createCategory_WhenCategoryDataValid_ShouldReturnCategoryResponseDto() {
        when(categoryRepository.findByName(categoryUpdateDto.getName())).thenReturn(Optional.empty());
        when(modelMapper.map(categoryUpdateDto, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(modelMapper.map(category, CategoryResponseDto.class)).thenReturn(categoryResponseDto);
        CategoryResponseDto actual = categoryServiceImpl.createCategory(categoryUpdateDto);
        assertThat(actual, is(categoryResponseDto));
    }

    @Test
    void updateCategory_WhenCategoryNull_ShouldThrowNewResourceNotFoundException() {
        when(categoryRepository.findByName(categoryUpdateDto.getName())).thenReturn(null);
        ResourceNotFoundException actual = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> categoryServiceImpl.updateCategory(category.getId(),categoryUpdateDto));
        assertThat("Category Not Found", is(actual.getMessage()));
    }

    @Test 
    void updateCategory_WhenCategoryDataValid_ShouldReturnCategoryResponseDto(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(modelMapper.map(categoryUpdateDto, Category.class)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(modelMapper.map(category,CategoryResponseDto.class)).thenReturn(categoryResponseDto);
        CategoryResponseDto actual = categoryServiceImpl.updateCategory(category.getId(), categoryUpdateDto);
        verify(categoryRepository).save(category);
        assertThat(categoryResponseDto, is(actual));
    }

    @Test
    void deteleCategory_WhenCategoryNull_ShouldThrowNewResourceNotFoundException() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());
        String responseActual = categoryServiceImpl.deleteCategory(category.getId()).getBody().getMessage();
        assertThat("Category Not Found", is(responseActual));
    }

    @Test
    void deteleCategory_WhenCategoryStillHasProduct_ShouldThrowNewBadRequestException() {
        List<Product> products = new ArrayList();
        products.add(product);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(productRepository.findByCategoryId(category.getId())).thenReturn(Optional.of(products));
        String responseActual = categoryServiceImpl.deleteCategory(category.getId()).getBody().getMessage();
        assertThat("Category still has products", is(responseActual));
    }

    @Test
    void deteleCategory_WhenCategoryDataValid_ShouldRenturnResponseEntity() {
        List<Product> products = new ArrayList();
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(productRepository.findByCategoryId(category.getId())).thenReturn(Optional.of(products));
        String responseActual = categoryServiceImpl.deleteCategory(category.getId()).getBody().getMessage();
        assertThat("Delete Successfully!", is(responseActual));
    }

}
