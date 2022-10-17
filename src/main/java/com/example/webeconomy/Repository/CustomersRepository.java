package com.example.webeconomy.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.Entities.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

    Customers findByAccountid(long accountid);
    List<Customers> findByName (String name);
    
}
