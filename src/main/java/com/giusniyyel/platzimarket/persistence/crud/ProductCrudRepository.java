package com.giusniyyel.platzimarket.persistence.crud;

import com.giusniyyel.platzimarket.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
    List<Product> findByIdCategoryOrderByNameAsc(int idCategory);

    Optional<List<Product>> findByStockLessThanAndStatus(int stock, boolean status);
}
