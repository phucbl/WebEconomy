package com.example.webeconomy.dto.request;

import com.example.webeconomy.constants.OrderStatus;

import lombok.Data;
@Data
public class OrderUpdateDto {
    private long customerId;
    private OrderStatus status;
}
