package pl.ekulka.ecommerce.catalog.storage;

import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.List;

public interface ProductStorage {
     List<Product> allProducts();
     void addProduct(Product newProduct);
     Product getProductBy(String id);
}
