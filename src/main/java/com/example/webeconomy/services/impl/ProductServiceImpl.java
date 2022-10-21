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
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId (int categoryId){
        return this.productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long id){
        Optional<Product> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found with Controller Advice");
        }
        Product product = productOptional.get();
        return product;

    }
    @Override
    public ProductResponseDto createProduct(ProductUpdateDto dto){
        Product product = modelMapper.map(dto,Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }
    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto){
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found");
        }
        
        Product product = productOptional.get();
        modelMapper.map(dto,product);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductResponseDto.class);
    }
}
