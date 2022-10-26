package com.giusniyyel.platzimarket.domain.service;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;
import com.giusniyyel.platzimarket.domain.repository.PurchaseRepositoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepositoryDTO purchaseRepositoryDTO;

    public PurchaseService(PurchaseRepositoryDTO purchaseRepositoryDTO) {
        this.purchaseRepositoryDTO = purchaseRepositoryDTO;
    }

    public List<PurchaseDTO> getAll() {
        return purchaseRepositoryDTO.getAll();
    }

    public Optional<List<PurchaseDTO>> getPurchasesByClient(String clientId) {
        return purchaseRepositoryDTO.getByClient(clientId);
    }

    public PurchaseDTO save(PurchaseDTO purchase) {
        return purchaseRepositoryDTO.save(purchase);
    }
}
