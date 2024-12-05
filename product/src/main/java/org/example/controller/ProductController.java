package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "main_methods")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product",
            description = "Creates a new product based on the provided ProductDTO.")
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all products",
            description = "Retrieves a list of all products.")
    public ResponseEntity<List<Product>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }
    @GetMapping("/{status}")
    @Operation(summary = "Find products by status",
            description = "Retrieves a list of products filtered by the specified status.")
    public ResponseEntity<List<Product>> findAllByStatus(@PathVariable String status) {
        return new ResponseEntity<>(productService.readFilter(status), HttpStatus.OK);
    }
    @GetMapping("/value")
    @Operation(summary = "Calculate total value of products",
            description = "Calculates and returns the total value of all products.")
    public ResponseEntity<Double> sumValue(){
        return new ResponseEntity<>(productService.sumValue(), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update an existing product",
            description = "Updates the details of an existing product based on the provided Product object.")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product",
            description = "Deletes the product with the specified ID.")
    public HttpStatus delete(@PathVariable Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
    @GetMapping("/load-csv")
    @Operation(summary = "Load products from CSV",
            description = "Loads product data from a CSV file.")
    public HttpStatus loadCsv() {
        productService.loadCsvData();
        return HttpStatus.OK;
    }
}