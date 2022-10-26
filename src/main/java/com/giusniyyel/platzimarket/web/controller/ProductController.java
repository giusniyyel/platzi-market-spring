package com.giusniyyel.platzimarket.web.controller;

import com.giusniyyel.platzimarket.domain.ProductDTO;
import com.giusniyyel.platzimarket.domain.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ApiOperation("Get all market products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<ProductDTO> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") int productId) {
        return productService.getProduct(productId).map(prod -> new ResponseEntity<>(prod, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Search products that belongs to a category with an ID")
    public ResponseEntity<List<ProductDTO>> getByCategoryId(@ApiParam(value = "The category id to search for the products", required = true, example = "1") @PathVariable("id") int categoryId) {
        return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation("Create a new product into the market")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a product with and ID")
    public ResponseEntity<Void> delete(@ApiParam(value = "The product id to delete", required = true, example = "6") @PathVariable("id") int productId) {
        return new ResponseEntity<>(productService.delete(productId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
