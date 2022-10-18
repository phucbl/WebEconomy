package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.webeconomy.entities.*;
import com.example.webeconomy.repositories.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomersController {
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private AccountsRepository accountsRepository;
    
    CustomersController (CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }
    
    @PostMapping("/customer")
        Customers newCustomers(@RequestBody Customers newCustomers){
            return customersRepository.save(newCustomers);
        }
    

    @GetMapping("/findAllCustomers")
    List<Customers> all(){
        return customersRepository.findAll();
    }

    @GetMapping("/customer")
    Customers getCustomerByAccountId(@RequestParam long accountId){
        return customersRepository.findByAccountId(accountId);
    }

    @PutMapping("/customer/{id}")
    Customers replaceCustomers(@RequestBody Customers newCustomers, @PathVariable long id){
        return customersRepository.findById(id)
            .map(customers -> {
            customers.setAccountId(newCustomers.getAccountId());
            customers.setAddress(newCustomers.getAddress());
            customers.setName(newCustomers.getName());
            return customersRepository.save(customers);
        })
        .orElseGet(() ->{
            newCustomers.setId(id);
            return customersRepository.save(newCustomers);

        });
    }
}
