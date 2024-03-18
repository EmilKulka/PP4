package pl.ekulka.ecommerce.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts() {
        return new ArrayList<>();
    }

    public void addProduct(String name, String description) {
        UUID id = UUID.randomUUID();

        Product newProduct = new Product(id, name, description);

        products.add(newProduct);
    }
}
