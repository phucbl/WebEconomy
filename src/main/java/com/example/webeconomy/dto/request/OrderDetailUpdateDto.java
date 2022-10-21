package com.example.webeconomy.dto.request;

import com.example.webeconomy.data.entities.OrderDetailId;

import lombok.Data;
@Data
public class OrderDetailUpdateDto {
    private OrderDetailId orderDetailId;
    private float price;
    private int quantity;
}
