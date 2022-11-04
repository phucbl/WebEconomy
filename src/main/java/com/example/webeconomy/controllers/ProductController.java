package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.webeconomy.dto.response.ProductResponseDto;
import com.example.webeconomy.dto.response.RatingResponseDto;
import com.example.webeconomy.dto.response.CartResponseDto;
import com.example.webeconomy.data.entities.Product;
import com.example.webeconomy.dto.request.CartUpdateDto;
import com.example.webeconomy.dto.request.ProductUpdateDto;
import com.example.webeconomy.dto.request.RatingUpdateDto;
import com.example.webeconomy.services.ProductService;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    
    @Autowired
    ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    List<Product> getProducts(){
        return productService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductResponseDto createProduct(@RequestBody ProductUpdateDto dto){
        return this.productService.createProduct(dto);
    }
    
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    CartResponseDto addToCart (@RequestBody CartUpdateDto dto){
        return this.productService.addToCart(dto);
    }

    @PostMapping("/{id}/rating")
    @ResponseStatus(HttpStatus.CREATED)
    RatingResponseDto addRating (@PathVariable("id") Long id, @Valid @RequestBody RatingUpdateDto dto){
        return this.productService.addRating(dto);
    }
    
    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto dto){
        return this.productService.updateProduct(id,dto);
    }
    @DeleteMapping("/{id}")
    ProductResponseDto deactiveProduct(@PathVariable("id") Long id){
        return productService.deactiveProduct(id);
    }

}
