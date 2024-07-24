package pl.ekulka.ecommerce.sales.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.sales.SalesFacade;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class OfferAcceptanceTest {

    @Autowired
    SalesFacade salesFacade;

    @Autowired
    ReservationServiceImpl reservationService;

    @Autowired
    ProductCatalogServiceImpl productCatalogService;



    @Test
    void itAllowToAcceptAnOffer() {
        String customerId = thereIsCustomer("1");
        Product product = thereIsProduct();
        var productId = product.getId();


        salesFacade.addToCart(customerId, productId);
        salesFacade.addToCart(customerId, productId);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstName("john")
                .setLastName("doe")
                .setEmail("john.doe@example.com");

        ReservationDetail reservationDetails = salesFacade.acceptOfferPayU(customerId, acceptOfferRequest);


        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();



        assertThereIsReservationWithId(reservationDetails.getReservationId());
        assertReservationIsPending(reservationDetails.getReservationId());
        assertReservationIsDoneForCustomer(reservationDetails.getReservationId(), "John", "Doe", "john.doe@example.com");
        assertReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(20));

    }

    private void assertReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationService.load(reservationId).get();


        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void assertReservationIsDoneForCustomer(String reservationId, String fname, String lname, String email) {
        Reservation loaded = reservationService.load(reservationId)
                .get();

        ClientDetails clientData = loaded.getCustomerDetails();

        assertThat(clientData.getFirstName()).isEqualTo(fname);
        assertThat(clientData.getLastName()).isEqualTo(lname);
        assertThat(clientData.getEmail()).isEqualTo(email);

    }

    private void assertReservationIsPending(String reservationId) {
        Reservation loaded = reservationService.load(reservationId)
                .get();

        assertThat(loaded.isPending()).isTrue();
    }

    private void assertThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationService.load(reservationId);

        assertThat(loaded).isPresent();
    }


    private Product thereIsProduct() {
        Product product = new Product(
                "Test name",
                "Test description",
                BigDecimal.valueOf(10)
        );

        productCatalogService.createProduct(product);
        return product;
    }

    private String thereIsCustomer(String id) {
        return id;
    }


}
