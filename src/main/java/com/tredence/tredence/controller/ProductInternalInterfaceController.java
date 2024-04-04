package com.tredence.tredence.controller;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/product")
@Validated
public class ProductInternalInterfaceController {

        @Autowired
        private ProductService productService;

        // POST http://localhost:8080/api/product/add
        @PostMapping("/add")
        public ResponseEntity<String> addProducts(@Valid @RequestBody List<ProductDTO> productDTOs, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                // Handle validation errors
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + bindingResult.getAllErrors());
            }
            productService.addProducts(productDTOs);
            return ResponseEntity.status(HttpStatus.CREATED).body("Products added successfully");
        }


    }


