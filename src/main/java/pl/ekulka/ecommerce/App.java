package pl.ekulka.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ekulka.ecommerce.catalog.storage.ArrayListProductStorage;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;

import java.math.BigDecimal;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalogTemp createMyProductCatalog() {
        ProductCatalogTemp productCatalog = new ProductCatalogTemp(new ArrayListProductStorage());
        productCatalog.addProduct("Lego set 1", "nice one", BigDecimal.valueOf(1000));
        productCatalog.addProduct("Lego set 2", "nice one", BigDecimal.valueOf(1000));
        productCatalog.addProduct("Lego set 3", "nice one", BigDecimal.valueOf(1000));

        return productCatalog;
    }

}
