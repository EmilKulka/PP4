package pl.ekulka.ecommerce.catalog;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.model.ProductDto;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ProductCatalogHttpTest {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate http;
    @Autowired
    ProductCatalogServiceImpl service;

    String URL;
    ProductDto productDto;
    UUID productId;


    @BeforeEach
    void setUp() {
        URL = String.format("http://localhost:%s/%s", port, "api/products");
        productDto = exampleProductDto();
        productId = service.getProducts().get(0).getId();
    }

    /**
     * Database is initialized with one Product:
     * id = Auto generated UUID
     * name = "Example Name"
     * description = "Example Description"
     * price = 100
     */

    @Test
    void itLoadsAllProductsFromRepository() {
        ResponseEntity<CollectionModel<Product>> response = http.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        CollectionModel<Product> products = response.getBody();

        assertThat(products).isNotNull();
        assertThat(products.getContent().size()).isEqualTo(2);
        assertThat(products.getContent())
                .extracting(Product::getName)
                .contains("Example Product");
        assertThat(products.getContent())
                .extracting(Product::getDescription)
                .contains("Example Description");
        assertThat(products.getContent())
                .extracting(Product::getPrice)
                .contains(BigDecimal.valueOf(100));
    }


    @Test
    void itLoadsProductByIdFromRepository() {
        ResponseEntity<EntityModel<Product>> response = http.exchange(
                URL + String.format("/%s", productId.toString()),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        EntityModel<Product> product = response.getBody();

        assertThat(product).isNotNull();
        assertThat(product.getContent())
                .extracting(Product::getId)
                .isEqualTo(productId);
        assertThat(product.getContent())
                .extracting(Product::getName)
                .isEqualTo("Example Product");
        assertThat(product.getContent())
                .extracting(Product::getDescription)
                .isEqualTo("Example Description");
        assertThat(product.getContent())
                .extracting(Product::getPrice)
                .isEqualTo(BigDecimal.valueOf(100));
    }


    @Test
    void itCreatesNewProduct() {
        HttpEntity<ProductDto> request = new HttpEntity<>(exampleProductDto());

        ResponseEntity<EntityModel<Product>> response = http.exchange(
                URL,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {
                }
        );
        EntityModel<Product> product = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(product).isNotNull();
        assertThat(product.getContent().getId()).isNotNull();
        assertThat(product.getContent().getName()).isEqualTo("DTO NAME");
        assertThat(product.getContent().getDescription()).isEqualTo("DTO DESCRIPTION");
        assertThat(product.getContent().getPrice()).isEqualTo(BigDecimal.valueOf(1000));
    }

    // TODO: response body assertions
    @Test
    void itUpdatesProduct() {
        HttpEntity<ProductDto> request = new HttpEntity<>(exampleProductDto());

        ResponseEntity<EntityModel<Product>> response = http.exchange(
                URL + String.format("/%s", productId.toString()),
                HttpMethod.PUT,
                request,
                new ParameterizedTypeReference<>() {

                }
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void itDeletesProduct() {
        ResponseEntity<EntityModel<Object>> response = http.exchange(
                URL + String.format("/%s", productId.toString()),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    private ProductDto exampleProductDto() {
        return new ProductDto(
                "DTO NAME",
                "DTO DESCRIPTION",
                BigDecimal.valueOf(1000)
        );
    }
}


