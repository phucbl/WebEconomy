package com.example.webeconomy.dto.request;
import java.util.List;

import com.example.webeconomy.data.entities.*;

import lombok.Data;

@Data
public class CustomerCreateOrderUpdateDto {
    private OrderUpdateDto dto;
    private List<Cart> carts;
}

//Failed to evaluate Jackson deserialization for type 
//[[simple type, class com.example.webeconomy.dto.request.CustomerCreateOrderUpdateDto]]:
//com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
//Multiple back-reference properties with name 'defaultReference'