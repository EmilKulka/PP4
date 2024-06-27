package pl.ekulka.ecommerce.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.ProductCatalogService;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ProductServiceIntegrationTest {

    @Autowired
    private ProductCatalogService service;

    @Test
    void itAllowToAddProductToRepository() {
        // Given
        Product product = thereIsProduct(
                "Example Name",
                "Example Description",
                BigDecimal.valueOf(100));
        String productID = product.getId();
        String productName = product.getName();
        String productDescription = product.getDescription();
        BigDecimal productPrice = product.getPrice();

        // When
        service.addProduct(product);
        List<Product> allProducts = service.allProducts();
        Optional<Product> foundProduct = service.getProductById(productID);

        // Then
        assertThat(productID).isEqualTo(foundProduct.get().getId());
        assertThat(productName).isEqualTo(foundProduct.get().getName());
        assertThat(productDescription).isEqualTo(foundProduct.get().getDescription());
        assertThat(productPrice).isEqualTo(foundProduct.get().getPrice());
        assertThat(allProducts).hasSize(1);
    }

    @Test
    void itThrowsExceptionWhenProductNotFoundByID() {
        assertThatThrownBy(() -> service.getProductById("fake ID"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Product with ID fake ID not found");
    }

    private Product thereIsProduct(String name, String description, BigDecimal price) {
        UUID id = UUID.randomUUID();
        return new Product(id,
                name,
                description,
                price);
    }
}
