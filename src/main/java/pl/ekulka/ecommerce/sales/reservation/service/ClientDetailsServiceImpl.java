package pl.ekulka.ecommerce.sales.reservation.service;

import org.springframework.stereotype.Service;
import pl.ekulka.ecommerce.sales.reservation.model.ClientDetails;
import pl.ekulka.ecommerce.sales.reservation.repository.ClientDetailsRepository;

import java.util.Optional;
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService{
    private final ClientDetailsRepository repository;

    public ClientDetailsServiceImpl(ClientDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveClientData(ClientDetails clientDetails) {
        repository.save(clientDetails);
    }

    @Override
    public Optional<ClientDetails> getClientById(String clientID) {
        return Optional.ofNullable(repository.findById(clientID)
                .orElseThrow(() -> new RuntimeException("Client with ID " + clientID + " not found")
                ));
    }
}
