package pl.ekulka.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.ProductRepository;
import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.repository.ClientDetailsRepository;
import pl.ekulka.ecommerce.sales.reservation.repository.ReservationRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ClientDetailsRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public DbInit(ProductRepository repository, ClientDetailsRepository clientRepository, ReservationRepository reservationRepository) {
        this.productRepository = repository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    public void run(String... args) throws Exception {
        productRepository.save(new Product(
                "Example Product",
                "Example Description",
                BigDecimal.valueOf(100)));


        ClientDetails client = (new ClientDetails(
                "1",
                "John",
                "Doe",
                "john.doe@example.com"
        ));

         clientRepository.save(client);

         reservationRepository.save(new Reservation(
                 client,
                 BigDecimal.valueOf(100)
         ));
    }
}
