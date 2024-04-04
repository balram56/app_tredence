package com.tredence.tredence.controller;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.service.ShopperProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ExternalShopperProductController {

    private ShopperProductService shopperProductService;


    public ExternalShopperProductController(ShopperProductService shopperProductService) {
        this.shopperProductService = shopperProductService;
    }
      //http://localhost:8080/api/external/shopper-products

    @GetMapping("/external/shopper-products")
    public ResponseEntity<List<ProductDTO>> getShopperProducts(@RequestParam String shopperId,
                                                               @RequestParam(required = false) String category,
                                                               @RequestParam(required = false) String brand,
                                                               @RequestParam(defaultValue = "10") int limit) {
        List<ProductDTO> products = shopperProductService.getShopperProducts(shopperId, category, brand, limit);
        return ResponseEntity.ok(products);
    }
}
