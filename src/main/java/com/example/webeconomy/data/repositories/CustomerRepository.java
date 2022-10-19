package com.example.webeconomy.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByAccountId(Long accountId);
    List<Customer> findByName (String name);
    
}
