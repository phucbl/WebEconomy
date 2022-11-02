package com.example.webeconomy.dto.request;
import java.util.List;

import com.example.webeconomy.data.entities.*;

import lombok.Data;

@Data
public class CustomerCreateOrderUpdateDto {
    private OrderUpdateDto dto;
    private List<Cart> carts;
}
