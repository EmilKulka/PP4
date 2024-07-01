package pl.ekulka.ecommerce.repositories.reservationRepository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.service.ClientDetailsServiceImpl;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservationServiceIntegrationTest {

    @Autowired
    private ReservationServiceImpl reservationService;

    @Test
    void itAllowToAddReservationForGivenCustomer() {
        Reservation reservation = thereIsReservation();
        String reservationID = reservation.getId();
        String clientID = reservation.getCustomerDetails().getCustomerId();

        reservationService.add(reservation);
        Optional<Reservation> loadedReservation = reservationService.load(reservationID);

        assertThat(loadedReservation.get().getId()).isEqualTo(reservationID);
        assertThat(loadedReservation.get().getCustomerDetails().getCustomerId()).isEqualTo(clientID);
    }

    private Reservation thereIsReservation() {
        return new Reservation(
                UUID.randomUUID().toString(),
                thereIsClient(),
                BigDecimal.valueOf(100)
        );
    }

    private ClientDetails thereIsClient() {
        return new ClientDetails(
                "2",
                "John",
                "Doe",
                "john.doe@example.com"
        );
    }
}
