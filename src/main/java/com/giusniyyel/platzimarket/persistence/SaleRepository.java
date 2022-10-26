package com.giusniyyel.platzimarket.persistence;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;
import com.giusniyyel.platzimarket.domain.repository.PurchaseRepositoryDTO;
import com.giusniyyel.platzimarket.persistence.crud.SaleCrudRepository;
import com.giusniyyel.platzimarket.persistence.entity.Sale;
import com.giusniyyel.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaleRepository implements PurchaseRepositoryDTO {

    private final SaleCrudRepository saleCrudRepository;
    private final PurchaseMapper mapper;

    public SaleRepository(SaleCrudRepository saleCrudRepository, PurchaseMapper mapper) {
        this.saleCrudRepository = saleCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseDTO> getAll() {
        return mapper.toPurchases((List<Sale>) saleCrudRepository.findAll());
    }

    @Override
    public Optional<List<PurchaseDTO>> getByClient(String clientId) {
        return saleCrudRepository.findByIdClient(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchase) {
        Sale sale = mapper.toSale(purchase);
        sale.getProducts().forEach(product -> product.setSale(sale));

        return mapper.toPurchase(saleCrudRepository.save(sale));
    }
}
