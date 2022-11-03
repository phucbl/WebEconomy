package com.example.webeconomy.dto.response;
import com.example.webeconomy.data.entities.OrderDetailId;
import com.example.webeconomy.data.entities.Product;

import lombok.Data;
@Data
public class OrderDetailResponseDto {
    private OrderDetailId id;
    private float price;
    private int quantity;
    private String productName;
    private String imageUrl;
}

