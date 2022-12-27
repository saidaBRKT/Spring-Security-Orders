package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findByUuid(UUID uuid);
    int deleteByUuid(UUID uuid);
}
