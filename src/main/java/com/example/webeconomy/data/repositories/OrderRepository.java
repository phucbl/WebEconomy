package com.example.webeconomy.data.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}
