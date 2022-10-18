package com.example.webeconomy.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.entities.*;
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findById(int id);
    
}
