package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.response.CustomerResponseDto;
import com.example.webeconomy.dto.response.ErrorResponse;
import com.example.webeconomy.data.entities.Customer;
import com.example.webeconomy.dto.request.CustomerUpdateDto;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.services.CustomerService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    
    @Autowired
    CustomerController (CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
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
