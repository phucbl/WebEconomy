package com.example.webeconomy.data.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailid> {
    List<OrderDetail> findByIdOrderId(Long orderId);
    
}