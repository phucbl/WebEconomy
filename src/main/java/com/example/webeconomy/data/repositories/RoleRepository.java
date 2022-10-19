package com.example.webeconomy.data.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
    
}
