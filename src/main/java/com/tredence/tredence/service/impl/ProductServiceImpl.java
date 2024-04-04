package com.tredence.tredence.service.impl;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.entity.Product;
import com.tredence.tredence.exception.ProductIdExistsException;
import com.tredence.tredence.exception.ProductNotFoundException;
import com.tredence.tredence.repository.ProductRepository;
import com.tredence.tredence.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void addProducts(List<ProductDTO> products) {
        for (ProductDTO productDTO : products) {
            String productId = productDTO.getProductId();
            boolean productIdExists = productRepository.existsByProductId(productId);
            if (productIdExists) {
                throw new ProductIdExistsException("Product with productId " + productId + " already exists");
            }
        }

        List<Product> productEntities = products.stream()
                .map(productDTO -> modelMapper.map(productDTO, Product.class))
                .collect(Collectors.toList());

        productRepository.saveAll(productEntities);
    }
    @Override
    public List<ProductDTO> getProductsByCategoryAndBrand(String category, String brand, int limit, String sortBy, String orderBy, int page) {
        Page<Product> productsPage = productRepository.findAllByCategoryAndBrand(category, brand, PageRequest.of(page, limit));

        // Extract the list of products from the page
        List<Product> products = productsPage.getContent();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found for the given category and brand");
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
