package com.product.management.product_management.service;

import com.product.management.product_management.dto.ProductDTO;
import com.product.management.product_management.mapper.ProductMapper;
import com.product.management.product_management.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;


    public List<ProductDTO> getAllProducts(){

        return productRepository.findAll().stream()
                .map(ProductMapper::mapToProductDTO)
                .toList();
    }

    public ProductDTO getProductById(Long id){

        return productRepository.findById(id)
                .map(ProductMapper::mapToProductDTO)
                .orElseGet(()-> {
                    return ProductDTO
                            .builder()
                            .build();
                });
    }

    public ProductDTO updateProductData(ProductDTO productDTO){

        if(productRepository.existsById(productDTO.getId())){
           var savedData  =  productRepository.save(ProductMapper.mapToProduct(productDTO));
           return ProductMapper.mapToProductDTO(savedData);
        }
        return null;
    }

    public void deleteProduct(Long id ){
         productRepository.deleteById(id);
         log.info("the product is deleted with this _ : " + id);
    }

    public ProductDTO createProdcut(ProductDTO productDTO) {

        if(productRepository.existsById(productDTO.getId())) {
            throw new RuntimeException("The product is already present in the database");
        }
      var savedData =   productRepository.save(ProductMapper.mapToProduct(productDTO));

        return ProductMapper.mapToProductDTO(savedData);
    }
}
