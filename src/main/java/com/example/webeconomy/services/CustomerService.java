package com.example.webeconomy.services;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;

import com.example.webeconomy.dto.response.*;

@Service
public interface CustomerService {

    public List<Customer> getAllCustomers();
    public List<Cart> getCartByCustomerId(Long id);
    public List<Order> getOrderByCustomerId(Long id);
    public CustomerResponseDto getCustomerById(Long id);
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto);
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto);
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto);
    public CartResponseDto updateCart(CartUpdateDto dto);
    public ResponseEntity<ResponseDto> deleteCart(ProductCustomerId id);
}
