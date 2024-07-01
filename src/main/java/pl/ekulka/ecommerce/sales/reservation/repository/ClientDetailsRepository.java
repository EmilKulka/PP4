package pl.ekulka.ecommerce.sales.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {
}
