package pl.ekulka.ecommerce.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;

import java.util.List;

@RestController
public class ProductCatalogTempController {
    ProductCatalogTemp catalog;

    public ProductCatalogTempController(ProductCatalogTemp catalog) {
        this.catalog = catalog;
    }

    @GetMapping("api/products")
    List<Product> allProducts() {
        return catalog.allProducts();
    }
}
