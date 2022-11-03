package com.example.webeconomy.dto.request;

import org.springframework.boot.context.properties.ConstructorBinding;

import com.example.webeconomy.data.entities.OrderDetailid;

import lombok.Data;
@Data
public class OrderDetailUpdateDto {
    private OrderDetailid id;
    private float price;
    private int quantity;
}
