package com.product.management.product_management.mapper;


import com.product.management.product_management.dto.ProductDTO;
import com.product.management.product_management.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDTO mapToProductDTO(Product product) {

        return ProductDTO.builder()
                .id(product.getId())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }

    public static Product mapToProduct(ProductDTO product){

        return Product.builder()
                .id(product.getId())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }
}
