//package pl.ekulka.ecommerce.catalog;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import pl.ekulka.ecommerce.catalog.model.Product;
//import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//public class HttpProductCatalogTempTest {
//
//    @LocalServerPort
//    private int localPort;
//    @Autowired
//    TestRestTemplate http;
//    @Autowired
//    ProductCatalogTemp productCatalog;
//    @Test
//    void homepageLoads() {
//        var url = String.format("http://localhost:%s/%s", localPort, "/");
//
//        ResponseEntity<String> response = http.getForEntity(url, String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).contains("My Ecommerce");
//    }
//
//    @Test
//    void loadsProducts() {
//        var id = productCatalog.addProduct("Example Product", "test", BigDecimal.valueOf(10));
//        var url = String.format("http://localhost:%s/%s", localPort, "/api/in-memory/products");
//
//        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody())
//                .extracting("name")
//                .contains("Example Product");
//        assertThat(response.getBody().length).isEqualTo(4); // catalog initialized with 3 products already
//        assertThat(response.getBody())
//                .extracting("id")
//                .containsOnlyOnce(id);
//
//    }
//}
