package com.giusniyyel.platzimarket.persistence.mapper;

import com.giusniyyel.platzimarket.domain.PurchaseItemDTO;
import com.giusniyyel.platzimarket.persistence.entity.SaleProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // Product Mapper is required just to ignore it.
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProduct", target = "productId"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "status", target = "active")
    })
    PurchaseItemDTO toPurchaseItem(SaleProduct product);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "sale", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "id.idSale", ignore = true)
    })
    SaleProduct toSaleProduct(PurchaseItemDTO item);
}
