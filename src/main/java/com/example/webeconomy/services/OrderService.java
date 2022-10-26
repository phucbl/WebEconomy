package com.example.webeconomy.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webeconomy.constants.OrderStatus;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderUpdateDto;
import com.example.webeconomy.dto.response.OrderResponseDto;

@Service
public interface OrderService {

    public List<Order> getAllOrders();
    public List<Order> getOrderByCustomerId(Long customerId);
    public Order getOrderById(Long id);
    public OrderResponseDto createOrder(OrderUpdateDto dto);
    public OrderResponseDto updateOrder(Long id, OrderUpdateDto dto);
    public OrderResponseDto cancelOrder(Long id);
}
