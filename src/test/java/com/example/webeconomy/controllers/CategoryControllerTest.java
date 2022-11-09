package com.example.webeconomy.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.webeconomy.data.entities.Category;
import com.example.webeconomy.dto.request.CategoryUpdateDto;
import com.example.webeconomy.dto.response.CategoryResponseDto;
import com.example.webeconomy.dto.response.ResponseDto;
import com.example.webeconomy.exceptions.ResourceNotFoundException;
import com.example.webeconomy.services.CategoryService;
import com.example.webeconomy.services.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

        @Autowired
        ObjectMapper objectMapper;
        @Autowired
        private MockMvc mockMvc;
        @Mock
        private CategoryService categoryService;
        ResponseEntity<ResponseDto> responseEntity;
        Category category;
        CategoryUpdateDto categoryUpdateDto;
        CategoryResponseDto categoryResponseDto;
        CategoryController categoryController;

        @BeforeEach
        void setUp() {
                category = mock(Category.class);
                categoryUpdateDto = mock(CategoryUpdateDto.class);
                categoryResponseDto = mock(CategoryResponseDto.class);
                categoryService = mock(CategoryServiceImpl.class);
                responseEntity = mock(ResponseEntity.class);
                categoryController = new CategoryController(categoryService);
        }
        @Test
        void getAllCategories_ShouldReturnListCategoryResponseDto() throws Exception {
                List<CategoryResponseDto> categoryResponseDtos = new ArrayList();
                categoryResponseDtos.add(categoryResponseDto);
                when(categoryService.getAllCategories()).thenReturn(categoryResponseDtos);
                List<CategoryResponseDto> result = categoryController.getAllCategories();
                assertThat(categoryResponseDtos, is(result));
        }

        @Test
        void getCategoryById_WhenIdExist_ShouldReturnCategoryResponseDto() throws Exception {
                CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                when(categoryService.getCategoryById(category.getId())).thenReturn(categoryResponseDto);
                CategoryResponseDto result = categoryController.getCategoryById(category.getId());
                assertThat(categoryResponseDto, is(result));
        }

        @Test
        void getCategoryById_WhenIdNotExisted() throws Exception {
                when(categoryService.getCategoryById(0)).thenReturn(null);
                CategoryResponseDto result = categoryController.getCategoryById(0);
                assertThat(null, is(result));
        }
        @Test
        void createCategory_WhenNameNotExisted_ShouldReturnCategoryResponseDto() throws Exception {
                
                when(categoryService.createCategory(categoryUpdateDto)).thenReturn(categoryResponseDto);
                CategoryResponseDto result = categoryController.createCategory(categoryUpdateDto);
                assertThat(categoryResponseDto, is(result));
        }

        @Test
        void createCategory_WhenNameExisted_ShouldReturnItemExistException() throws Exception {
                when(categoryService.createCategory(categoryUpdateDto)).thenReturn(categoryResponseDto);
                CategoryResponseDto result = categoryController.createCategory(categoryUpdateDto);
                assertThat(categoryResponseDto, is(result));
        }

        @Test
        void updateCategory_WhenIdNotExisted() throws Exception {
                when(categoryService.updateCategory(category.getId(), categoryUpdateDto)).thenReturn(null);
                CategoryResponseDto result = categoryController.updateCategory(category.getId(), categoryUpdateDto);
                assertThat(null, is(result));
        }

        @Test
        void updateCategory_WhenIdExisted_ShouldReturnCategoryResponseDto() throws Exception {
                
                when(categoryService.updateCategory(category.getId(), categoryUpdateDto)).thenReturn(categoryResponseDto);
                CategoryResponseDto result = categoryController.updateCategory(category.getId(),categoryUpdateDto);
                assertThat(categoryResponseDto, is(result));
        }

        @Test
        void deleteCategory_WhenIdNotExisted()throws Exception{
                responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(null, "Category Not Found", "404"));
                when(categoryService.deleteCategory(0)).thenReturn(responseEntity);
                ResponseEntity result = categoryController.deleteCategory(category.getId());
                assertThat(responseEntity, is(result));
        }

        @Test
        void deleteCategory_WhenIdExisted_ShouldReturnResponseEntity()throws Exception{
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Successfully!", "200"));
                when(categoryService.deleteCategory(category.getId())).thenReturn(responseEntity);
                ResponseEntity result = categoryController.deleteCategory(category.getId());
                assertThat(responseEntity, is(result));
        }
}
