package com.tredence.tredence.repository;

import com.tredence.tredence.entity.Product;
import com.tredence.tredence.entity.Shopper;
import com.tredence.tredence.entity.ShopperProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long> {

    List<ShopperProduct> findByShopper(Shopper shopper);

    List<ShopperProduct> findByProduct(Product product);
    @Query("SELECT sp FROM ShopperProduct sp WHERE sp.shopper.shopperId = :shopperId AND sp.product.category = :category AND sp.product.brand = :brand")
    List<ShopperProduct> findShopperProducts(String shopperId, String category, String brand);

    // Example using pagination
    @Query("SELECT sp FROM ShopperProduct sp WHERE sp.shopper.shopperId = :shopperId AND sp.product.category = :category AND sp.product.brand = :brand")
    Page<ShopperProduct> findShopperProducts(String shopperId, String category, String brand, Pageable pageable);

    List<ShopperProduct> findByRelevancyScoreGreaterThan(Double relevancyScore);

@Query("SELECT sp FROM ShopperProduct sp WHERE sp.shopper.shopperId = :shopperId " +
        "AND (:category IS NULL OR sp.product.category = :category) " +
        "AND (:brand IS NULL OR sp.product.brand = :brand)")
List<ShopperProduct> findByShopperIdAndCategoryAndBrand(
        @Param("shopperId") String shopperId,
        @Param("category") String category,
        @Param("brand") String brand,
        Pageable pageable);

    Object findByShopper_ShopperId(String shopperId);
}
