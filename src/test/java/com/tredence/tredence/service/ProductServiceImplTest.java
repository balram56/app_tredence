package com.tredence.tredence.service;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.entity.Product;
import com.tredence.tredence.exception.ProductIdExistsException;
import com.tredence.tredence.exception.ProductNotFoundException;
import com.tredence.tredence.repository.ProductRepository;
import com.tredence.tredence.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testAddProducts() {
        // Test data
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId("testId");
        productDTO.setCategory("testCategory");
        productDTO.setBrand("testBrand");
        productDTOList.add(productDTO);

        // Mocking behavior for ProductRepository
        when(productRepository.existsByProductId("testId")).thenReturn(false);

        // Call the method to test
        assertDoesNotThrow(() -> productService.addProducts(productDTOList));

        // Verify that saveAll method is called with the correct arguments
        verify(productRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testAddProducts_ProductIdExists() {
        // Test data
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId("testId");
        productDTO.setCategory("testCategory");
        productDTO.setBrand("testBrand");
        productDTOList.add(productDTO);

        // Mocking behavior for ProductRepository to return true (product with the same ID already exists)
        when(productRepository.existsByProductId("testId")).thenReturn(true);

        // Call the method to test and verify that ProductIdExistsException is thrown
        assertThrows(ProductIdExistsException.class, () -> productService.addProducts(productDTOList));

        // Verify that saveAll method is not called
        verify(productRepository, never()).saveAll(anyList());
    }

    @Test
    void testGetProductsByCategoryAndBrand() {
        // Mocking behavior for ProductRepository to return a page with one product
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductId("testId");
        product.setCategory("testCategory");
        product.setBrand("testBrand");
        productList.add(product);
        Page<Product> productPage = new PageImpl<>(productList);
        when(productRepository.findAllByCategoryAndBrand("testCategory", "testBrand", PageRequest.of(0, 10))).thenReturn(productPage);

        // Mocking behavior for ModelMapper
        when(modelMapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(new ProductDTO());

        // Call the method to test
        List<ProductDTO> productDTOList = productService.getProductsByCategoryAndBrand("testCategory", "testBrand", 10, "name", "asc", 0);

        // Verify the result
        assertNotNull(productDTOList);
        assertFalse(productDTOList.isEmpty());
        assertEquals(1, productDTOList.size());
    }

    @Test
    void testGetProductsByCategoryAndBrand_NoProductsFound() {
        // Mocking behavior for ProductRepository to return an empty page
        Page<Product> emptyProductPage = new PageImpl<>(new ArrayList<>());
        when(productRepository.findAllByCategoryAndBrand("testCategory", "testBrand", PageRequest.of(0, 10))).thenReturn(emptyProductPage);

        // Call the method to test and verify that ProductNotFoundException is thrown
        assertThrows(ProductNotFoundException.class, () ->
                productService.getProductsByCategoryAndBrand("testCategory", "testBrand", 10, "name", "asc", 0));
    }
}
