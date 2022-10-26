package com.example.webeconomy.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface RatingRepository extends JpaRepository<Rating,ProductCustomerId>{
    List<Rating> findByIdProductId(Long productId);
}
