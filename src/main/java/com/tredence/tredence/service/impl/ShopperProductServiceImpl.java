package com.tredence.tredence.service.impl;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.dto.ShopperProductDTO;
import com.tredence.tredence.entity.Product;
import com.tredence.tredence.entity.Shopper;
import com.tredence.tredence.entity.ShopperProduct;
import com.tredence.tredence.repository.ProductRepository;
import com.tredence.tredence.repository.ShopperProductRepository;
import com.tredence.tredence.repository.ShopperRepository;
import com.tredence.tredence.service.ShopperProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopperProductServiceImpl implements ShopperProductService {

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopperProductRepository shopperProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShopperProductDTO createShopperProduct(ShopperProduct shopperProduct) {
        // Perform any business logic or validation before saving the ShopperProduct
        if (shopperProduct == null) {
            throw new IllegalArgumentException("ShopperProduct cannot be null");
        }

        // Save the shopperProduct
        ShopperProduct savedShopperProduct = shopperProductRepository.save(shopperProduct);

        // Convert the saved ShopperProduct back to DTO
        return modelMapper.map(savedShopperProduct, ShopperProductDTO.class);
    }

    private ShopperProductDTO convertToDTO(ShopperProduct shopperProduct) {
        return modelMapper.map(shopperProduct, ShopperProductDTO.class);
    }
    @Override
    public List<ProductDTO> getShopperProducts(String shopperId, String category, String brand, int limit) {
        return shopperProductRepository.findShopperProducts(shopperId, category, brand, Pageable.ofSize(limit))
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(ShopperProduct shopperProduct) {
        return modelMapper.map(shopperProduct, ProductDTO.class);
    }


}
