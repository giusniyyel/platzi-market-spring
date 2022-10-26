package com.giusniyyel.platzimarket.domain.repository;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepositoryDTO {
    List<PurchaseDTO> getAll();

    Optional<List<PurchaseDTO>> getByClient(String cliendId);

    PurchaseDTO save(PurchaseDTO purchase);
}
