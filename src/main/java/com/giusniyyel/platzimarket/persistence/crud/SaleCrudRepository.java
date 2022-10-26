package com.giusniyyel.platzimarket.persistence.crud;

import com.giusniyyel.platzimarket.persistence.entity.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SaleCrudRepository extends CrudRepository<Sale, Integer> {

    Optional<List<Sale>> findByIdClient(String idClient);
}
