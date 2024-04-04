package com.tredence.tredence.service;





import com.tredence.tredence.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void addProducts( List<ProductDTO> products);

    List<ProductDTO> getProductsByCategoryAndBrand(String category, String brand, int limit, String sortBy, String orderBy, int page);
}