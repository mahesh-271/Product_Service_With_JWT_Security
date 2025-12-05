package com.product.management.product_management.controller;


import com.product.management.product_management.dto.ProductDTO;
import com.product.management.product_management.service.ProductService;
import com.product.management.product_management.service.ProductServiceDuplicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public List<ProductDTO> getListOfProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProductByid(@PathVariable Long id){
        return productService.getProductById(id);
    }

//    @PostMapping("/createProduct")
//    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
//        return productService.createProduct(productDTO);
//    }

    @PutMapping("/updateProduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProductData(productDTO);
    }

//    @DeleteMapping("/deleteProduct/{id}")
//    public void  deleteProductById(@PathVariable Long id){
//        productService.deleteProduct(id);
//        log.info("Product deleted succcessfully");
//    }
}
