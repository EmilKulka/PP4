package pl.ekulka.ecommerce.catalog.service;

import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCatalogService {
    List<Product> allProducts();

    void addProduct(Product newProduct);

    Optional<Product> getProductById(String id);
}
