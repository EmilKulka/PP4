package pl.ekulka.ecommerce.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.infrastructure.PayUGateway;
import pl.ekulka.ecommerce.payu.PayU;
import pl.ekulka.ecommerce.payu.PayUCredentials;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationServiceImpl;

@Configuration
public class SalesConfiguration {

    final
    ProductCatalogServiceImpl productCatalogService;
    final ReservationServiceImpl reservationService;

    public SalesConfiguration(ProductCatalogServiceImpl productCatalogService, ReservationServiceImpl reservationService) {
        this.productCatalogService = productCatalogService;
        this.reservationService = reservationService;
    }

    @Bean
    SalesFacade createSales() {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(productCatalogService),
                new PayUGateway(createSandboxPayU()),
                reservationService
        );
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
}
