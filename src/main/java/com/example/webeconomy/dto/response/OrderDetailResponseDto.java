package com.example.webeconomy.dto.response;
import com.example.webeconomy.data.entities.OrderDetailid;
import com.example.webeconomy.data.entities.Product;

import lombok.Data;
@Data
public class OrderDetailResponseDto {
    private OrderDetailid id;
    private float price;
    private int quantity;
    private String productName;
    private String imageUrl;
}

