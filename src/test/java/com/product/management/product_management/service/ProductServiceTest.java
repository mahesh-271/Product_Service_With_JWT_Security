package com.product.management.product_management.service;

import com.product.management.product_management.dto.ProductDTO;
import com.product.management.product_management.entity.Product;
import com.product.management.product_management.mapper.ProductMapper;
import com.product.management.product_management.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts() {
        List<Product> input = List.of(
                Product.builder()
                        .id(1L)
                        .name("Messi")
                        .price(1111d)
                        .stock(33)
                        .description("Iphone")
                        .build()
        );

        when(productRepository.findAll()).thenReturn(input);

        var result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Messi", result.get(0).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        var product = Optional.of(
                Product.builder()
                        .id(1L)
                        .name("iPhone")
                        .price(1111d)
                        .stock(33)
                        .description("Iphone")
                        .build()
        );

        when(productRepository.findById(1L)).thenReturn(product);

        var result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("iPhone", result.getName());
    }

    @Test
    void updateProductData() {

        ProductDTO dto = ProductDTO.builder()
                .id(1L)
                .name("Updated")
                .price(2000d)
                .stock(50)
                .description("Updated Desc")
                .build();

        when(productRepository.existsById(1L)).thenReturn(true);

        Product savedEntity = ProductMapper.mapToProduct(dto);
        when(productRepository.save(any(Product.class))).thenReturn(savedEntity);

        var result = productService.updateProductData(dto);

        assertNotNull(result);
        assertEquals("Updated", result.getName());
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {

        Long id = 1L;

        doNothing().when(productRepository).deleteById(id);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void createProduct() {

        ProductDTO dto = ProductDTO.builder()
                .id(10L)
                .name("Laptop")
                .price(3000d)
                .stock(20)
                .description("Gaming Laptop")
                .build();

        when(productRepository.existsById(dto.getId())).thenReturn(false);

        Product savedEntity = ProductMapper.mapToProduct(dto);
        when(productRepository.save(any(Product.class))).thenReturn(savedEntity);

        var result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
