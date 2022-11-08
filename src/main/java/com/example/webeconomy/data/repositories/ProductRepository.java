package com.example.webeconomy.data.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webeconomy.data.entities.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByCategoryId(int categoryId);
    Optional<Product> findByName (String name);

}
