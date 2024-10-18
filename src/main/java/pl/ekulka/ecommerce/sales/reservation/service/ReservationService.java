package pl.ekulka.ecommerce.sales.reservation.service;

import pl.ekulka.ecommerce.sales.reservation.model.Reservation;

import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> load(Long reservationId);

    Reservation create(Reservation reservation);

     void setPayUOrderId(Long reservationId, String orderPauYOrderId);
}
