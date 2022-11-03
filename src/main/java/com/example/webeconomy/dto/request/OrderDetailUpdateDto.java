package com.example.webeconomy.dto.request;

import org.springframework.boot.context.properties.ConstructorBinding;

import com.example.webeconomy.data.entities.OrderDetailId;

import lombok.Data;
@Data
public class OrderDetailUpdateDto {
    private OrderDetailId id;
    private float price;
    private int quantity;
}
