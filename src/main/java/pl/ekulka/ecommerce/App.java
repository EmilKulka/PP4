package pl.ekulka.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
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
import pl.ekulka.ecommerce.sales.reservation.repository.ReservationRepositoryTemp;
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
    NameValidator nameValidator() {
        return new NameValidator();
    }

    @Bean
    DescriptionValidator descriptionValidator() {
        return new DescriptionValidator();
    }

    @Bean
    PriceValidator priceValidator() {
        return new PriceValidator();
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
    PayU createSandboxPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }

    @Bean
    SalesFacade createSales(ProductDetailsProvider productDetailsProvider) {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(productDetailsProvider),
                new PayUGateway(createSandboxPayU()),
                new ReservationRepositoryTemp()
        );
    }

    @Bean
    ProductDetailsProvider createProductDetailsProvider(ProductCatalogTemp catalog) {
        return new ProductCatalogProductDetailsProvider(catalog);
    }

}
