package pl.ekulka.ecommerce.sales.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ekulka.ecommerce.sales.SalesFacade;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class OfferAcceptanceTest {

    private SpyPaymentGateway spyPaymentGateway;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        spyPaymentGateway = new SpyPaymentGateway();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void itAllowToAcceptAnOffer() {
        SalesFacade sales = thereIsSales();
        String customerId = thereIsCustomer("Emil");
        String productId = ThereIsProduct("X", BigDecimal.valueOf(10));

        sales.addToCart(customerId,productId);
        sales.addToCart(customerId,productId);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstName("john")
                .setLastName("doe")
                .setEmail("john.doe@example.com");

        ReservationDetail reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();

        assertPaymentHasBeenRegistered();
        assertThereIsReservationWithId(reservationDetails.getReservationId());
        assertReservationIsPending(reservationDetails.getReservationId());
        assertReservationIsDoneForCustomer(reservationDetails.getReservationId(), "john", "doe", "john.doe@example.com");
        assertReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(20));

    }

    private void assertReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void assertReservationIsDoneForCustomer(String reservationId, String fname, String lname, String email) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        ClientDetails clientData = loaded.getCustomerDetails();

        assertThat(clientData.getFirstName()).isEqualTo(fname);
        assertThat(clientData.getLastName()).isEqualTo(lname);
        assertThat(clientData.getEmail()).isEqualTo(email);

    }

    private void assertReservationIsPending(String reservationId) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.isPending()).isTrue();
    }

    private void assertThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationRepository.load(reservationId);

        assertThat(loaded).isPresent();
    }

    private void assertPaymentHasBeenRegistered() {
        assertThat(spyPaymentGateway.getRequestCount()).isEqualTo(1);
    }

    private String ThereIsProduct(String productId, BigDecimal price) {
        return productId;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                spyPaymentGateway,
                reservationRepository);
    }
}
