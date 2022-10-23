package com.giusniyyel.platzimarket.persistence.mapper;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.persistence.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProduct", target = "productId"),
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "salePrice", target = "price"),
            @Mapping(source = "status", target = "active"),
    })
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductsDTO(List<Product> products);

    @InheritInverseConfiguration
    @Mapping(target = "codeBar", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
