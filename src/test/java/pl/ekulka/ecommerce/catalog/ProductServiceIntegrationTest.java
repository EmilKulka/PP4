package pl.ekulka.ecommerce.catalog;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ProductServiceIntegrationTest {

    @Autowired
    private ProductCatalogServiceImpl service;

    @Test
    void itAllowToAddProductToRepository() {
        // Given
        Product product = thereIsProduct(
                "Example Name",
                "Example Description",
                BigDecimal.valueOf(100)
        );
        UUID productID = service.createProduct(product).getId();
        String productName = product.getName();
        String productDescription = product.getDescription();
        BigDecimal productPrice = product.getPrice();

        // When
        List<Product> allProducts = service.getProducts();
        Product foundProduct = service.getProductById(productID);

        // Then
        assertThat(productID).isEqualTo(foundProduct.getId());
        assertThat(productName).isEqualTo(foundProduct.getName());
        assertThat(productDescription).isEqualTo(foundProduct.getDescription());
        assertThat(productPrice).isEqualTo(foundProduct.getPrice());
        assertThat(allProducts).hasSize(2); // DB initialized with 1 product already
    }

    @Test
    void itThrowsExceptionWhenProductNotFoundByID() {
        var randomId = UUID.randomUUID();
        assertThatThrownBy(() -> service.getProductById(randomId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product with id " + randomId + " not found");
    }

    private Product thereIsProduct(String name, String description, BigDecimal price) {
        return new Product(
                name,
                description,
                price);
    }
}
