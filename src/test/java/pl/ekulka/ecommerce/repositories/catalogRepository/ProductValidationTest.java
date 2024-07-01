package pl.ekulka.ecommerce.repositories.catalogRepository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductValidationTest {

    @Autowired
    private ProductCatalogServiceImpl productService;

    private Product validProduct;

    @BeforeEach
    void setUp() {
        validProduct = new Product(
                UUID.randomUUID(),
                "Example name",
                "Example description",
                BigDecimal.valueOf(100)
        );
    }

    @Test
    void shouldNotThrowErrorsWithValidProductData() {
        Errors errors = new BeanPropertyBindingResult(validProduct, "product");
        productService.addProduct(validProduct);

        assertThat(errors.hasErrors()).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        validProduct.changeName(null);

        assertThatThrownBy(() -> productService.addProduct(validProduct))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Name cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsInvalid() {
        validProduct.changeDescription("");

        assertThatThrownBy(() -> productService.addProduct(validProduct))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Description must be between 1 and 200 characters");
    }

    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {
        validProduct.changePrice(BigDecimal.valueOf(-1));

        assertThatThrownBy(() -> productService.addProduct(validProduct))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Price cannot be lower than 0");
    }
}
