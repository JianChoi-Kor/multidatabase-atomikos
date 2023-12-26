package com.example.multidatabase.first.repository;

import com.example.multidatabase.first.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
