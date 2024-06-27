package pl.ekulka.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.HashMapProductStorage;
import pl.ekulka.ecommerce.catalog.storage.ProductStorage;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class HashMapProductStorageTest {

    @Test
    void itAllowToStoreProduct() {
        Product product = thereIsExampleProduct();

        ProductStorage hasMapStorage = thereIsExampleHashMapStorage();

        hasMapStorage.addProduct(product);

        List<Product> products = hasMapStorage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains("test-it");



    }

    private ProductStorage thereIsExampleHashMapStorage() {
        return new HashMapProductStorage();
    }

    private Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(), "test-it", "test", BigDecimal.valueOf(10));
    }

    @Test
    void itAllowsToLoadAllProduct() {

    }

    @Test
    void itAllowsToLoadProductById() {

    }
}
