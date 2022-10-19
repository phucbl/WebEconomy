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
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerServices;
    
    @Autowired
    CustomerController (CustomerService customerServices){
        this.customerServices = customerServices;
    }

    @GetMapping
    List<Customer> getCustomers(){
        return customerServices.getAllCustomers();
    }

    @GetMapping("/{id}")
    Customer getCustomerById(@PathVariable("id") Long id){
        return customerServices.getCustomerById(id);
    }
    @GetMapping("/with-dto/{id}")
    CustomerResponseDto getCustomerByIdDto(@PathVariable("id") Long id){
        return customerServices.getCustomerByIdDto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerResponseDto createCustomer(@Valid @RequestBody CustomerUpdateDto dto){
        return this.customerServices.createCustomer(dto);
    }
    
    @PutMapping("/{id}")
    CustomerResponseDto updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerUpdateDto dto){
        return this.customerServices.updateCustomer(id,dto);
    }

}
