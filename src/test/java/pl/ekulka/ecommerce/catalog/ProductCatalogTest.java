package pl.ekulka.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsListingProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new ArrayListProductStorage());
    }

    @Test
    void itAllowsToAddProduct() {
        ProductCatalog catalog = thereIsProductCatalog();

        catalog.addProduct("Legoset 8083", "nice one");

        List<Product> allProducts = catalog.allProducts();

        assertThat(allProducts)
                .hasSize(1);
    }

    @Test
    void itLoadSingleProductById() {
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Legoset 8083", "nice one");

        Product loaded = catalog.getProductBy(id);

        assertThat(loaded.getId())
                .isEqualTo(id);
    }

    @Test void itAllowsToChangePrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Legoset 8083", "nice one");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));

        Product loaded = catalog.getProductBy(id);

        assertThat(loaded.getPrice())
                .isEqualTo(BigDecimal.valueOf(10.10));
    }
}
