package pl.ekulka.ecommerce.catalog.storage;

import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ArrayListProductStorage implements ProductStorage {
    private ArrayList<Product> products;

    public ArrayListProductStorage() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts() {
        return products;
    }

    public Product getProductBy(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }

    public void addProduct(Product newProduct) {
        products.add(newProduct);
    }




}


