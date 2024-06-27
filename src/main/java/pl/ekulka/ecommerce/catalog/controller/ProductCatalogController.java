package pl.ekulka.ecommerce.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.ProductCatalog;

import java.util.List;

@RestController
public class ProductCatalogController {

    ProductCatalog catalog;

    public ProductCatalogController(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("api/products")
    List<Product> allProducts() {
        return catalog.allProducts();
    }
}
