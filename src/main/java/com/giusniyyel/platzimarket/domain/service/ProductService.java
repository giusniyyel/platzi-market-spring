package com.giusniyyel.platzimarket.domain.service;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.domain.repository.ProductRepositoryDTO;
import com.giusniyyel.platzimarket.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepositoryDTO productRepositoryDTO;

    @Autowired
    public ProductService(ProductRepositoryDTO productRepositoryDTO) {
        this.productRepositoryDTO = productRepositoryDTO;
    }

    public List<ProductDTO> getAll() {
        return productRepositoryDTO.getAll();
    }

    public Optional<ProductDTO> getProduct(int productId) {
        return productRepositoryDTO.getProduct(productId);
    }

    public Optional<List<ProductDTO>> getByCategory(int categoryId) {
        return productRepositoryDTO.getByCategory(categoryId);
    }

    public ProductDTO save(ProductDTO product) {
        return productRepositoryDTO.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(prod -> {
            productRepositoryDTO.delete(productId);
            return true;
        }).orElse(false);
    }
}
