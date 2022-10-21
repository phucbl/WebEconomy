package com.example.webeconomy.dto.request;

import java.util.Date;

import lombok.Data;
@Data
public class OrderUpdateDto {
    private Date dateCreated;
    private int status;
    private long customerId;
}
