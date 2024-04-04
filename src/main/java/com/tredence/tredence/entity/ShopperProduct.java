package com.tredence.tredence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated primary key

    @ManyToOne
    @JoinColumn(name = "shopperId")
    private Shopper shopper; // Represents the many-to-one relationship with Shopper entity

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product; // Represents the many-to-one relationship with Product entity

    private Double relevancyScore;

    public void setShopper(Shopper shopper) {
    }

    public void setShopperId(String s) {
    }

    public void setProductId(String s) {
    }


    // Getters and setters
}

