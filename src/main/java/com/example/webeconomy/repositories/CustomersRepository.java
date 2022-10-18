package com.example.webeconomy.repositories;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.entities.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

    Customers findByAccountId(long accountId);
    List<Customers> findByName (String name);
    
}
