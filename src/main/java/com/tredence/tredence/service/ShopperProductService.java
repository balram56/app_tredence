package com.tredence.tredence.service;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.dto.ShopperProductDTO;
import com.tredence.tredence.entity.ShopperProduct;

import java.util.List;

public interface ShopperProductService {

    ShopperProductDTO createShopperProduct(ShopperProduct shopperProductDTO);
    List<ProductDTO> getShopperProducts(String shopperId, String category, String brand, int limit);

}
