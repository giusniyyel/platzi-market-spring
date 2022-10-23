package com.giusniyyel.platzimarket.persistence;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.domain.repository.ProductRepositoryDTO;
import com.giusniyyel.platzimarket.persistence.crud.ProductCrudRepository;
import com.giusniyyel.platzimarket.persistence.entity.Product;
import com.giusniyyel.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductRepositoryDTO {
    private ProductCrudRepository productCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return mapper.toProductsDTO(products);
    }

    @Override
    public Optional<List<ProductDTO>> getByCategory(int idCategory) {
        List<Product> products = productCrudRepository.findByIdCategoryOrderByNameAsc(idCategory);
        return Optional.of(mapper.toProductsDTO(products));
    }

    @Override
    public Optional<List<ProductDTO>> getScarseProducts(int quantity) {
        Optional<List<Product>> products = productCrudRepository.findByStockLessThanAndStatus(quantity, true);
        return products.map(prods -> mapper.toProductsDTO(prods));
    }

    @Override
    public Optional<ProductDTO> getProduct(int idProduct) {
        return productCrudRepository.findById(idProduct).map(prod -> mapper.toProductDTO(prod));
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        Product prod = mapper.toProduct(product);
        return mapper.toProductDTO(productCrudRepository.save(prod));
    }

    @Override
    public void delete(int idProduct) {
        productCrudRepository.deleteById(idProduct);
    }
}
