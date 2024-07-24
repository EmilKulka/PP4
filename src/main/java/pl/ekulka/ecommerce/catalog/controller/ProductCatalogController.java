package pl.ekulka.ecommerce.catalog.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ekulka.ecommerce.catalog.model.ProductDto;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.catalog.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductCatalogController {
    private final ProductCatalogServiceImpl service;

    public ProductCatalogController(ProductCatalogServiceImpl service) {
        this.service = service;
    }


    @GetMapping("api/products")
    public ResponseEntity<List<Product>> allProducts() {
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("api/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("api/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productdto) {
        Product product = service.createProduct(new Product(
                productdto.getName(),
                productdto.getDescription(),
                productdto.getPrice()
        ));
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("api/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody ProductDto productDto) {
        Product productToBeModified = service.getProductById(id);

        productToBeModified.setName(productDto.getName());
        productToBeModified.setDescription(productDto.getDescription());
        productToBeModified.changePrice(productDto.getPrice());
        productToBeModified.setModifiedAt(LocalDateTime.now());

        service.updateProduct(productToBeModified);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") UUID id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
