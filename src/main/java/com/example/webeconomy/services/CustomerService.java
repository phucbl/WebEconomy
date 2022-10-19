package com.example.webeconomy.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.CustomerUpdateDto;
import com.example.webeconomy.dto.response.CustomerResponseDto;

@Service
public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(Long id);
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto);
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto);
}
