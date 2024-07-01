package pl.ekulka.ecommerce.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.List;

@RestController
public class ProductCatalogController {
    final
    ProductCatalogServiceImpl service;

    public ProductCatalogController(ProductCatalogServiceImpl service) {
        this.service = service;
    }


    @GetMapping("api/productsDB")
    List<Product> allProducts() {
        return service.allProducts();
    }

}
