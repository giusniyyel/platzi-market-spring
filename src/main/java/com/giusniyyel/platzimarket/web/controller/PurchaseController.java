package com.giusniyyel.platzimarket.web.controller;

import com.giusniyyel.platzimarket.domain.PurchaseDTO;
import com.giusniyyel.platzimarket.domain.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Get all purchases")
    public ResponseEntity<List<PurchaseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getAll());
    }

    @GetMapping("/client/{id}")
    @ApiOperation("Get all purchases made by a client with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Purchases not found")
    })
    public ResponseEntity<List<PurchaseDTO>> getPurchasesByClient(@ApiParam(value = "The id client", required = true, example = "4546221") @PathVariable("id") String idClient) {
        return purchaseService.getPurchasesByClient(idClient).map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation("Save a new purchase")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<PurchaseDTO> save(@RequestBody PurchaseDTO purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
