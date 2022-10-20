package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.response.ProductResponseDto;
import com.example.webeconomy.dto.response.ErrorResponse;
import com.example.webeconomy.data.entities.Product;
import com.example.webeconomy.dto.request.ProductUpdateDto;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.services.ProductService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/Product")
public class ProductController {
    private ProductService ProductService;
    
    @Autowired
    ProductController (ProductService ProductService){
        this.ProductService = ProductService;
    }

    @GetMapping
    List<Product> getProducts(){
        return ProductService.getAllProducts();
    }
    @GetMapping("/")
    List<Product> getProductsByCategoryId(@RequestParam("categoryId") int categoryId ){
        return ProductService.getAllProductsByCategoryId(categoryId);
    }
    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id){
        return ProductService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductResponseDto createProduct(@RequestBody ProductUpdateDto dto){
        return this.ProductService.createProduct(dto);
    }
    
    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto dto){
        return this.ProductService.updateProduct(id,dto);
    }

}
