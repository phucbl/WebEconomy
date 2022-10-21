package com.example.webeconomy.dto.response;

import java.sql.Date;

import lombok.Data;
@Data
public class OrderResponseDto {
    private long id;
    private Date dateCreated;
    private int status;
    private long customerId;
}
