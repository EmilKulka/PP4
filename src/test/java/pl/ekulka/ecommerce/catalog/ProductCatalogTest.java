package pl.ekulka.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsListingProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }
}
