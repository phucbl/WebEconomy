package com.example.webeconomy.data.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webeconomy.data.entities.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName (String name);
}