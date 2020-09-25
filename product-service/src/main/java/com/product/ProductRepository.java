package com.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
