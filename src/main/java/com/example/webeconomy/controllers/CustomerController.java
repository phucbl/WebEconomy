package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.dto.response.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.services.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    CustomerController (CustomerService customerService){

        this.customerService = customerService;
    }

    @GetMapping
    List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/")
    CustomerResponseDto getCustomerByHeaderId(){
        return customerService.getCustomerByToken();
    }

    @GetMapping("/cart")
    List<CartResponseDto> getCartByHeaderCustomerId(@RequestHeader("customerId") Long id){
        return customerService.getCartByCustomerId(id);
    }
    @GetMapping("/{id}")
    CustomerResponseDto getCustomerById(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/order")
    List<Order> getOrderByCustomerId(){
        return customerService.getOrdersByCustomerId();
    }
    @GetMapping("/order/{id}")
    OrderResponseDto getOrderByCustomerIdOrderId(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/cart")
    OrderResponseDto createOrder(@RequestBody CreateOrderDto createOrderDto){
        return customerService.createOrder(createOrderDto);
    }

    @PutMapping("/cart")
    CartResponseDto updateCart(@RequestBody CartUpdateDto dto){
        return customerService.updateCart(dto);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<ResponseDto> deleteCart(@RequestBody ProductCustomerId id){
        return customerService.deleteCart(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDto createCustomer(@RequestBody CustomerUpdateDto dto){
        return this.customerService.createCustomer(dto);
    }
    
    @PutMapping("/{id}")
    CustomerResponseDto updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerUpdateDto dto){
        return this.customerService.updateCustomer(id,dto);
    }

}
