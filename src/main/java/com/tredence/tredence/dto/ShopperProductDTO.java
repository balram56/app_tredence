package com.tredence.tredence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductDTO {
    @NotBlank(message = "Shopper ID is required")
    private String shopperId;

    @NotBlank(message = "Product ID is required")
    private String productId;

    private Double relevancyScore;

    public void setId(long l) {
    }




    // Getters and setters
}
