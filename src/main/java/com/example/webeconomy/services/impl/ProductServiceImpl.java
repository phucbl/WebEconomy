package com.example.webeconomy.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.ProductUpdateDto;
import com.example.webeconomy.dto.response.ProductResponseDto;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository ProductRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository ProductRepository, ModelMapper modelMapper){
        this.ProductRepository = ProductRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> getAllProducts(){
        return this.ProductRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId (int categoryId){
        return this.ProductRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long id){
        Optional<Product> ProductOptional = this.ProductRepository.findById(id);

        if (ProductOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found with Controller Advice");
        }
        Product Product = ProductOptional.get();
        return Product;

    }
    @Override
    public ProductResponseDto createProduct(ProductUpdateDto dto){
        Product Product = modelMapper.map(dto,Product.class);
        Product savedProduct = ProductRepository.save(Product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }
    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto){
        Optional<Product> ProductOptional = this.ProductRepository.findById(id);
        if (ProductOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found");
        }
        
        Product Product = ProductOptional.get();
        modelMapper.map(dto,Product);
        Product = ProductRepository.save(Product);
        return modelMapper.map(Product, ProductResponseDto.class);
    }
}
