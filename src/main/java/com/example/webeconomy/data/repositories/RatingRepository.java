package com.example.webeconomy.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface RatingRepository extends JpaRepository<Rating,ProductCustomerId>{
    
}
