package pl.ekulka.ecommerce.sales.reservation.service;

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

    public Optional<Reservation> load(String reservationId) {
        return Optional.ofNullable(repository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation with ID " + reservationId + " not found")
                ));
    }

    public void add(Reservation reservation) {
        repository.save(reservation);
    }

}
