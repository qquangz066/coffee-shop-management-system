package com.coffeeshop.admin.repository;

import com.coffeeshop.admin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
