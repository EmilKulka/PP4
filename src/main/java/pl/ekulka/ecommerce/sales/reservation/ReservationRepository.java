package pl.ekulka.ecommerce.sales.reservation;

import pl.ekulka.ecommerce.sales.reservation.model.Reservation;

import java.util.HashMap;
import java.util.Optional;

public class ReservationRepository {
    public HashMap<String, Reservation> reservations;

    public ReservationRepository() {
        this.reservations = new HashMap<>();
    }

    public Optional<Reservation> load(String reservationId) {
        return Optional.of(reservations.get(reservationId));
    }

    public void add(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }
}
