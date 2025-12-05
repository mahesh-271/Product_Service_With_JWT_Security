package com.product.management.product_management.service;

import com.product.management.product_management.dto.ProductDTO;
import com.product.management.product_management.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceDuplicate {

    private final List <ProductDTO> productDTOS = new ArrayList<>();

    public List<ProductDTO> getAllProducts() {

        return productDTOS;
    }

    public ProductDTO getProductById(Long id) {

        return   productDTOS.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .orElse(ProductDTO.builder().build());

    }

    public ProductDTO updateProductData(ProductDTO productDTO) {

      return   productDTOS.stream()
                .filter(product -> product.getId() == productDTO.getId())
                .map(result -> ProductMapper.mapToProductDTO1(productDTO))
                .findFirst()
                .orElse(null);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {

        productDTOS.add(productDTO);
        return productDTO;
    }
}



