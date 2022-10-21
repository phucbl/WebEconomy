package com.example.webeconomy.dto.response;
import com.example.webeconomy.data.entities.OrderDetailId;

import lombok.Data;
@Data
public class OrderDetailResponseDto {
    private OrderDetailId ordersDetailid;
    private float price;
    private int quantity;
}

