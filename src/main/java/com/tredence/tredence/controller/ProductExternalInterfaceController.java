package com.tredence.tredence.controller;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prod_uct")
@Validated
public class ProductExternalInterfaceController {

    @Autowired
    private ProductService productService;
   // http://localhost:8080/api/prod_uct?category=Babies&brand=Girlds


    @GetMapping
    public List<ProductDTO> getProductsByCategoryAndBrand(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        return productService.getProductsByCategoryAndBrand(category, brand, limit, sortBy, orderBy, page);
    }
}
