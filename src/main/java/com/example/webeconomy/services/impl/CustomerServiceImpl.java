package com.example.webeconomy.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.CustomerUpdateDto;
import com.example.webeconomy.dto.response.CustomerResponseDto;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }  
    @Override
    public Customer getCustomerById(Long id){
        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();
        return customer;

    }
    @Override
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto){
        Customer customer = modelMapper.map(dto,Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponseDto.class);
    }
    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto){
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer Not Found");
        }
        
        Customer customer = customerOptional.get();
        modelMapper.map(dto,customer);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDto.class);
    }
}
