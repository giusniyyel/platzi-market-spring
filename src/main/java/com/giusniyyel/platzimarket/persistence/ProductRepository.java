package com.giusniyyel.platzimarket.persistence;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.domain.repository.ProductRepositoryDTO;
import com.giusniyyel.platzimarket.persistence.crud.ProductCrudRepository;
import com.giusniyyel.platzimarket.persistence.entity.Product;
import com.giusniyyel.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductRepositoryDTO {

    private final ProductCrudRepository productCrudRepository;
    private final ProductMapper mapper;

    public ProductRepository(ProductCrudRepository productCrudRepository, ProductMapper mapper) {
        this.productCrudRepository = productCrudRepository;
        this.mapper = mapper;
    }

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
        return products.map(mapper::toProductsDTO);
    }

    @Override
    public Optional<ProductDTO> getProduct(int idProduct) {
        return productCrudRepository.findById(idProduct).map(mapper::toProductDTO);
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
