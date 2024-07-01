package pl.ekulka.ecommerce.catalog.service;

import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.ProductStorage;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalogTemp {
    ProductStorage productStorage;

    public ProductCatalogTemp(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String description, BigDecimal price) {
        UUID id = UUID.randomUUID();

        Product newProduct = new Product(id, name, description, price);

        productStorage.addProduct(newProduct);

        return id.toString();
    }

    public Product getProductBy(String id) {
        return productStorage.getProductBy(id);
    }

    public void changePrice(String id, BigDecimal price) {
        Product loadedProduct = getProductBy(id);
        loadedProduct.changePrice(price);
    }

}
