package com.tredence.tredence.repository;


import com.tredence.tredence.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String> {
    Object findByShopperId(String s);
}
