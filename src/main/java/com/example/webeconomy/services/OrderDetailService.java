package com.example.webeconomy.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderDetailUpdateDto;
import com.example.webeconomy.dto.response.OrderDetailResponseDto;

@Service
public interface OrderDetailService {

    public List<OrderDetail> getAllOrderDetails();
    public List<OrderDetailResponseDto> getOrderDetailByOrderId(Long orderId);
    public OrderDetailResponseDto createOrderDetail(OrderDetailUpdateDto dto);
    public OrderDetailResponseDto updateOrderDetail(Long id, OrderDetailUpdateDto dto);
}

