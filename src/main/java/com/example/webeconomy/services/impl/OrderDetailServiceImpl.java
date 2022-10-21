package com.example.webeconomy.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
        return this.orderDetailRepository.findAll();
    }  
    @Override
    public List<OrderDetail> getOrderDetailByOrderId(Long orderId){
        return this.orderDetailRepository.findByOrderDetailIdOrderId(orderId);
    }  
    
    @Override
    public OrderDetailResponseDto createOrderDetail(OrderDetailUpdateDto dto){
        OrderDetail orderDetail = modelMapper.map(dto,OrderDetail.class);
        orderDetail.setOrdersDetailid(dto.getOrderDetailId());
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return modelMapper.map(savedOrderDetail, OrderDetailResponseDto.class);
    }
    @Override
    public OrderDetailResponseDto updateOrderDetail(Long id, OrderDetailUpdateDto dto){
        Optional<OrderDetail> OrderDetailOptional = this.orderDetailRepository.findById(id);
        if (OrderDetailOptional.isEmpty()){
            throw new ResourceNotFoundException("OrderDetail Not Found");
        }
        
        OrderDetail OrderDetail = OrderDetailOptional.get();
        modelMapper.map(dto,OrderDetail);
        OrderDetail = orderDetailRepository.save(OrderDetail);
        return modelMapper.map(OrderDetail, OrderDetailResponseDto.class);
    }
}

