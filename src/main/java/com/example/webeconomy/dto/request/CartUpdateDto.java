package com.example.webeconomy.dto.request;

import com.example.webeconomy.data.entities.ProductCustomerId;

import lombok.Data;

@Data
public class CartUpdateDto {
    private ProductCustomerId id;
    private int quantity;
}
