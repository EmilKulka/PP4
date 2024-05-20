package pl.ekulka.ecommerce.sales;

import pl.ekulka.ecommerce.sales.cart.Cart;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.sales.reservation.ReservationDetail;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offerCalculator;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        Offer currentOffer = offerCalculator.calculate(cart.getLines());

        return currentOffer;
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        return new ReservationDetail();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
