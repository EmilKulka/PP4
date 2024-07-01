package pl.ekulka.ecommerce.sales.reservation.service;

import pl.ekulka.ecommerce.sales.reservation.model.Reservation;

import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> load(String reservationId);

    void add(Reservation reservation);
}
