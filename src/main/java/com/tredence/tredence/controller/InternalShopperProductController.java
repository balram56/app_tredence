package com.tredence.tredence.controller;

import com.tredence.tredence.dto.ShopperProductDTO;
import com.tredence.tredence.entity.ShopperProduct;
import com.tredence.tredence.service.ShopperProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class InternalShopperProductController {

    @Autowired
    private ShopperProductService shopperProductService;

    @Autowired
    private ModelMapper modelMapper;
     //http://localhost:8080/api/internal/shopper-products
    @PostMapping("/internal/shopper-products")
    public ResponseEntity<ShopperProductDTO> createShopperProduct(@Validated @RequestBody ShopperProductDTO shopperProductDTO) {
        // Convert ShopperProductDTO to ShopperProduct
        ShopperProduct shopperProduct = modelMapper.map(shopperProductDTO, ShopperProduct.class);

        // Call the service method with the converted ShopperProduct
        ShopperProductDTO createdShopperProductDTO = shopperProductService.createShopperProduct(shopperProduct);

        // Return the response with the created DTO
        return new ResponseEntity<>(createdShopperProductDTO, HttpStatus.CREATED);
    }
}
