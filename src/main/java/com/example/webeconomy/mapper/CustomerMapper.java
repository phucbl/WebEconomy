package com.example.webeconomy.mapper;

import com.example.webeconomy.data.entities.Customer;
import com.example.webeconomy.dto.response.CustomerResponseDto;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponseDto mapEntityToDto(Customer customer){
        return CustomerResponseDto.builder()
        .id(customer.getId())
        .name(customer.getName())
        .address(customer.getAddress())
        .build();
    }

}
