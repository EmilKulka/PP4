package pl.ekulka.ecommerce.sales.reservation.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.repository.ReservationRepository;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository repository;

    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    public Optional<Reservation> load(Long reservationId) {
        return Optional.ofNullable(repository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with ID " + reservationId + " not found")
                ));
    }

    @Transactional
    public void setPayUOrderId(Long reservationId, String orderPauYOrderId) {
        Optional<Reservation> reservationToBeUpdated = load(reservationId);

        reservationToBeUpdated.get().setPayuOrderId(orderPauYOrderId);
    }

    public Reservation create(Reservation reservation) {
        return repository.save(reservation);
    }

}
