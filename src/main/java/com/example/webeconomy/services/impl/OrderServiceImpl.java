package com.example.webeconomy.services.impl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderUpdateDto;
import com.example.webeconomy.dto.response.OrderResponseDto;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository OrderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository OrderRepository, ModelMapper modelMapper){
        this.OrderRepository = OrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Order> getAllOrders(){
        return this.OrderRepository.findAll();
    }  
    @Override
    public List<Order> getOrdersByCustomerId(Long customerId){
        return this.OrderRepository.findByCustomerId(customerId);
    }  
    @Override
    public Order getOrderById(Long id){
        Optional<Order> OrderOptional = this.OrderRepository.findById(id);

        if (OrderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found with Controller Advice");
        }
        Order Order = OrderOptional.get();
        return Order;

    }
    @Override
    public OrderResponseDto createOrder(OrderUpdateDto dto){
        dto.setDateCreated(Calendar.getInstance().getTime());
        Order Order = modelMapper.map(dto,Order.class);
        Order savedOrder = OrderRepository.save(Order);
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }
    @Override
    public OrderResponseDto updateOrder(Long id, OrderUpdateDto dto){
        Optional<Order> OrderOptional = this.OrderRepository.findById(id);
        if (OrderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found");
        }
        
        Order Order = OrderOptional.get();
        modelMapper.map(dto,Order);
        Order = OrderRepository.save(Order);
        return modelMapper.map(Order, OrderResponseDto.class);
    }
}

