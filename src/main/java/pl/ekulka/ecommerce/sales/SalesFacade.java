package pl.ekulka.ecommerce.sales;

import pl.ekulka.ecommerce.payu.OrderCreateRequest;
import pl.ekulka.ecommerce.sales.cart.Cart;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.infrastructure.PaymentGateway;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.ReservationDetail;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationService;

import java.util.UUID;

public class SalesFacade {
    private final InMemoryCartStorage cartStorage;
    private final OfferCalculator offerCalculator;
    private final PaymentGateway paymentGateway;
    private final ReservationService reservationRepository;


    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationService reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        Offer currentOffer = offerCalculator.calculateOffer(cart.getLines());

        return currentOffer;
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer
        );

        reservationRepository.add(reservation);

        return new ReservationDetail(reservationId, paymentDetails.getPaymentUrl(), offer.getTotal());
    }

    public ReservationDetail acceptOfferPayU(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                OrderCreateRequest.of(reservationId, acceptOfferRequest, offer.getTotal(), offer)
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer
        );

        reservationRepository.add(reservation);

        return new ReservationDetail(reservationId, paymentDetails.getPaymentUrl(), offer.getTotal());
    }


    public void addToCart(String customerId, UUID productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);

        cartStorage.save(customerId, cart);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }
}
