package com.example.webeconomy.dto.response;

import java.sql.Date;

import com.example.webeconomy.constants.OrderStatus;

import lombok.Data;
@Data
public class OrderResponseDto {
    private long id;
    private Date dateCreated;
    private OrderStatus status;
    private long customerId;
}
