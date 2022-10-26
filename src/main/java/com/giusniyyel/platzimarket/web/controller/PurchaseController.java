package com.giusniyyel.platzimarket.web.controller;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;
import com.giusniyyel.platzimarket.domain.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAll());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<PurchaseDTO>> getPurchasesByClient(@PathVariable("id") String idClient) {
        return purchaseService.getPurchasesByClient(idClient).map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> save(@RequestBody PurchaseDTO purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
