package pl.ekulka.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ekulka.ecommerce.catalog.ArrayListProductStorage;
import pl.ekulka.ecommerce.catalog.ProductCatalog;
import pl.ekulka.ecommerce.infrastructure.PayUPaymentGateway;
import pl.ekulka.ecommerce.sales.SalesFacade;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.sales.reservation.ReservationRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("TEST");
        SpringApplication.run(App.class,args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new ArrayListProductStorage());
        productCatalog.addProduct("Lego set 1", "nice one");
        productCatalog.addProduct("Lego set 2", "nice one");
        productCatalog.addProduct("Lego set 3", "nice one");

        return productCatalog;
    }

    @Bean
    SalesFacade createSales(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new PayUPaymentGateway(),
                new ReservationRepository()
        );
    }
}
