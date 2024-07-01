package pl.ekulka.ecommerce.sales.reservation.service;

import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;

import java.util.Optional;

public interface ClientDetailsService {
    void saveClientData(ClientDetails clientDetails);

    Optional<ClientDetails> getClientById(String id);
}
