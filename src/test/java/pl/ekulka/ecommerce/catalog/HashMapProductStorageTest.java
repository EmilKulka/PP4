package pl.ekulka.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HashMapProductStorageTest {

    @Test
    void itAllowToStoreProduct() {
        Product product = thereIsExampleProduct();

        ProductStorage hasMapStorage = thereIsExampleHashMapStorage();

        hasMapStorage.add(product);

        List<Product> products = hasMapStorage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains("test-it");



    }

    private ProductStorage thereIsExampleHashMapStorage() {
        return new HashMap<,Product>();
    }

    private Product thereIsExampleProduct() {
        return new Product(1,"test-it","a");
    }

    @Test
    void itAllowsToLoadAllProduct() {

    }

    @Test
    void itAllowsToLoadProductById() {

    }
}
