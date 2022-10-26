package com.example.webeconomy.dto.request;

import java.util.Date;

import com.example.webeconomy.constants.OrderStatus;

import lombok.Data;
@Data
public class OrderUpdateDto {
    private long customerId;
    private OrderStatus status;
}
