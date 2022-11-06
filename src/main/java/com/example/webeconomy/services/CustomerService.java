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

    public List<CustomerResponseDto> getAllCustomers();
    public List<CartResponseDto> getCartByCustomerId(Long id);
    public List<Order> getOrdersByCustomerId();
    public CustomerResponseDto getCustomerByToken();
    public CustomerResponseDto getCustomerById(Long id);
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto);
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto);
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto);
    public CartResponseDto updateCart(CartUpdateDto dto);
    public ResponseEntity<ResponseDto> checkAccountExisted (String phoneNumber);
    public ResponseEntity<ResponseDto> deleteCart(ProductCustomerId id);
    public ResponseEntity<ResponseDto> changePassword(ChangePasswordDto dto);
}
