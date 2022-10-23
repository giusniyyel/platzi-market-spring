package com.giusniyyel.platzimarket.web.controller;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    public Optional<ProductDTO> getProduct(int productId) {
        return productService.getProduct(productId);
    }

    public Optional<List<ProductDTO>> getByCategoryId(int categoryId) {
        return productService.getByCategory(categoryId);
    }

    public ProductDTO save(ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    public boolean delete(int productId) {
        return productService.delete(productId);
    }
}
