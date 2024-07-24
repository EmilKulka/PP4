package pl.ekulka.ecommerce.catalog.storage;

import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HashMapProductStorage implements ProductStorage {
    HashMap<UUID, Product> products;

    public HashMapProductStorage() {
        this.products = new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return products.values()
                .stream().toList();
    }

    @Override
    public void addProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public Product getProductBy(String id) {
        return products.get(id);
    }
}
