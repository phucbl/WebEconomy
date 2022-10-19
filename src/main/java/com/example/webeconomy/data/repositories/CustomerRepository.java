package com.example.webeconomy.data.repositories;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByAccountId(long accountId);
    List<Customer> findByName (String name);
    
}
