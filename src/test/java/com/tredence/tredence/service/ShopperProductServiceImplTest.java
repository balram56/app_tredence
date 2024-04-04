package com.tredence.tredence.service;

import com.tredence.tredence.dto.ProductDTO;
import com.tredence.tredence.dto.ShopperProductDTO;
import com.tredence.tredence.entity.ShopperProduct;
import com.tredence.tredence.repository.ShopperProductRepository;
import com.tredence.tredence.service.impl.ShopperProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopperProductServiceImplTest {

    @Mock
    private ShopperProductRepository shopperProductRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ShopperProductServiceImpl shopperProductService;

    @Test
    void testCreateShopperProduct() {
        // Test data
        ShopperProduct shopperProduct = new ShopperProduct();
        shopperProduct.setId(1L);
        ShopperProductDTO shopperProductDTO = new ShopperProductDTO();
        shopperProductDTO.setId(1L);

        // Mocking behavior for ShopperProductRepository
        when(shopperProductRepository.save(any(ShopperProduct.class))).thenReturn(shopperProduct);

        // Mocking behavior for ModelMapper
        when(modelMapper.map(any(ShopperProduct.class), eq(ShopperProductDTO.class))).thenReturn(shopperProductDTO);

        // Call the method to test
        ShopperProductDTO result = shopperProductService.createShopperProduct(shopperProduct);



        // Verify that the save method is called once
        verify(shopperProductRepository, times(1)).save(any(ShopperProduct.class));
    }

}




