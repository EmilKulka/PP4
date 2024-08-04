package pl.ekulka.ecommerce.catalog.controller;


import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ekulka.ecommerce.catalog.model.ProductDto;
import pl.ekulka.ecommerce.catalog.model.ProductMapper;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ProductCatalogController {
    private final ProductCatalogServiceImpl service;
    private final ProductMapper productMapper;

    public ProductCatalogController(ProductCatalogServiceImpl service, ProductMapper productMapper) {
        this.service = service;
        this.productMapper = productMapper;
    }


    @GetMapping("api/products")
    public ResponseEntity<CollectionModel<Product>> getProducts() {
        List<Product> products = service.getProducts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CollectionModel.of(products,
                linkTo(methodOn(ProductCatalogController.class).getProducts()).withSelfRel()
        ));
    }

    @GetMapping("api/products/{id}")
    public ResponseEntity<EntityModel<Product>> getProduct(@PathVariable("id") UUID id) {
        Product product = service.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK)
                        .body(EntityModel.of(product,
                linkTo(methodOn(ProductCatalogController.class).getProduct(id)).withSelfRel(),
                linkTo(methodOn(ProductCatalogController.class).getProducts()).withRel("products")
        ));
    }

    @PostMapping("api/products")
    public ResponseEntity<EntityModel<Product>> createProduct(@Valid @RequestBody ProductDto productdto) {
        Product product = service.createProduct(
                productMapper.toEntity(
                        productdto
                ));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityModel.of(product,
                        linkTo(methodOn(ProductCatalogController.class).getProduct(product.getId())).withSelfRel(),
                        linkTo(methodOn(ProductCatalogController.class).getProducts()).withRel("products")
                ));
    }

    @PutMapping("api/products/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody ProductDto productDto) {
        Product product = service.updateProduct(productMapper.toEntity(
                productDto,
                id
        ));
        return ResponseEntity.status(HttpStatus.OK)
                .body(EntityModel.of(product,
                linkTo(methodOn(ProductCatalogController.class).getProduct(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductCatalogController.class).getProducts()).withRel("products")
        ));
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") UUID id) {
        service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
