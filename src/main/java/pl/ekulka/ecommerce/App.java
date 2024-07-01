package pl.ekulka.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.catalog.storage.ArrayListProductStorage;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;
import pl.ekulka.ecommerce.payu.PayU;
import pl.ekulka.ecommerce.payu.PayUCredentials;
import pl.ekulka.ecommerce.sales.SalesFacade;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.infrastructure.PayUGateway;
import pl.ekulka.ecommerce.sales.productdetails.ProductCatalogProductDetailsProvider;
import pl.ekulka.ecommerce.sales.productdetails.ProductDetailsProvider;
import pl.ekulka.ecommerce.sales.reservation.repository.ReservationRepository;
import pl.ekulka.ecommerce.sales.reservation.repository.ReservationRepositoryTemp;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationServiceImpl;
import pl.ekulka.validator.product.DescriptionValidator;
import pl.ekulka.validator.product.NameValidator;
import pl.ekulka.validator.product.PriceValidator;

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

    @Bean
    ProductDetailsProvider createProductDetailsProvider(ProductCatalogTemp catalog) {
        return new ProductCatalogProductDetailsProvider(catalog);
    }

}
