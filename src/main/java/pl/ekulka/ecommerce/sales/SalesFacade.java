package pl.ekulka.ecommerce.sales;

public class SalesFacade {
    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        return new ReservationDetail();
    }

    public void addToCart(String customerId, String productId) {
    }
}
