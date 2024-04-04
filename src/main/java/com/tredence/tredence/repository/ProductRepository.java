package com.tredence.tredence.repository;

import com.tredence.tredence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAllByCategoryAndBrand(String category, String brand, Pageable pageable);

    boolean existsByProductId(String productId);

    Object findByProductId(String s);
}
