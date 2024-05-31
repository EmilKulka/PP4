package pl.ekulka.ecommerce.sales.cart;

import pl.ekulka.ecommerce.sales.cart.Cart;

import java.util.Optional;

public class InMemoryCartStorage {
    public Optional<Cart> findByCustomer(String customerId) {
        return Optional.empty();
    }
}
