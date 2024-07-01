package pl.ekulka.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.ekulka.ecommerce.catalog.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ProductCatalogHttpTest {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate http;
    @Test
    void itLoadsProductsFromRepository() {
        String url = String.format("http://localhost:%s/%s",
                port,
                "api/productsDB"
        );

        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);

        assertThat(response.getBody().length).isEqualTo(1);
        assertThat(response.getBody())
                .extracting("name")
                .contains("Example Product");
        assertThat(response.getBody())
                .extracting("description")
                .contains("Example description");
        assertThat(response.getBody())
                .extracting("price")
                .contains(BigDecimal.valueOf(100));

    }
}
