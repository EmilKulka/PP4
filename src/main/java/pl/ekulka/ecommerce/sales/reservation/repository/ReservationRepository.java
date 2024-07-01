package pl.ekulka.ecommerce.sales.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
