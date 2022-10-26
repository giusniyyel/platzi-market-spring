package com.giusniyyel.platzimarket.persistence.mapper;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;
import com.giusniyyel.platzimarket.persistence.entity.Sale;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idSale", target = "purchaseId"),
            @Mapping(source = "idClient", target = "clientId"),
            @Mapping(source = "paymentType", target = "paymentMethod"),
            @Mapping(source = "products", target = "items"),
    })
    PurchaseDTO toPurchase(Sale sale);
    List<PurchaseDTO> toPurchases(List<Sale> sales);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Sale toSale(PurchaseDTO purchaseDTO);
}
