package pl.ekulka.ecommerce.catalog.service;

import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductCatalogService {
    List<Product> getProducts();

    Product createProduct(Product newProduct);

    Product getProductById(UUID id);
}
