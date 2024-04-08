package pl.ekulka.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalog {
    ArrayListProductStorage arrayListProductStorage;

    public ProductCatalog() {
        this.arrayListProductStorage = arrayListProductStorage;
    }

    public List<Product> allProducts() {
        return arrayListProductStorage.allProducts();
    }

    public String addProduct(String name, String description) {
        UUID id = UUID.randomUUID();

        Product newProduct = new Product(id, name, description);

        arrayListProductStorage.addProduct(newProduct);

        return id.toString();
    }

    public Product getProductBy(String id) {
        return arrayListProductStorage.getProductBy(id);
    }

    public void changePrice(String id, BigDecimal price) {
        Product loadedProduct = getProductBy(id);
        loadedProduct.changePrice(price);
    }

}
