package com.example.webeconomy.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderDetailUpdateDto;
import com.example.webeconomy.dto.response.OrderDetailResponseDto;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ModelMapper modelMapper){
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailRepository.findAll();
    }  
    @Override
    public List<OrderDetailResponseDto> getOrderDetailByOrderId(Long orderId){
        List<OrderDetail> orderDetails = orderDetailRepository.findByIdOrderId(orderId);
        List<OrderDetailResponseDto> orderDetailResponseDtos = modelMapper.map(orderDetails,new TypeToken<List<OrderDetailResponseDto>>() {}.getType());
        for (OrderDetailResponseDto orderDetailResponseDto : orderDetailResponseDtos) {
            Long productId=orderDetailResponseDto.getId().getProductId();
            Optional<Product> productOptional = productRepository.findById(productId);
            Product product=productOptional.get();
            orderDetailResponseDto.setProductName(product.getName());
            orderDetailResponseDto.setImageUrl(product.getImageUrl());
        }

        return orderDetailResponseDtos;
    }  
    
    @Override
    public OrderDetailResponseDto createOrderDetail(OrderDetailUpdateDto dto){
        OrderDetail orderDetail = modelMapper.map(dto,OrderDetail.class);
        orderDetail.setId(dto.getId());
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return modelMapper.map(savedOrderDetail, OrderDetailResponseDto.class);
    }
    @Override
    public OrderDetailResponseDto updateOrderDetail(Long id, OrderDetailUpdateDto dto){
        Optional<OrderDetail> OrderDetailOptional = orderDetailRepository.findById(id);
        if (OrderDetailOptional.isEmpty()){
            throw new ResourceNotFoundException("OrderDetail Not Found");
        }
        
        OrderDetail OrderDetail = OrderDetailOptional.get();
        modelMapper.map(dto,OrderDetail);
        OrderDetail = orderDetailRepository.save(OrderDetail);
        return modelMapper.map(OrderDetail, OrderDetailResponseDto.class);
    }
}

