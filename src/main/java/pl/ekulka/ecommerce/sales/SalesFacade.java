package pl.ekulka.ecommerce.sales;

import pl.ekulka.ecommerce.payu.PaymentRequest;
import pl.ekulka.ecommerce.payu.PaymentResponse;
import pl.ekulka.ecommerce.sales.cart.Cart;
import pl.ekulka.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.EveryNthProductDiscountPolicy;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.infrastructure.PaymentGateway;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;
import pl.ekulka.ecommerce.sales.reservation.ReservationDetail;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationService;

import java.util.UUID;

public class SalesFacade {
    private final InMemoryCartStorage cartStorage;
    private final OfferCalculator offerCalculator;
    private final PaymentGateway paymentGateway;
    private final ReservationService reservationService;
    private final EveryNthProductDiscountPolicy everyNthProductDiscountPolicy;


    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationService reservationService, EveryNthProductDiscountPolicy everyNthProductDiscountPolicy) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationService = reservationService;
        this.everyNthProductDiscountPolicy = everyNthProductDiscountPolicy;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);

        Offer currentOffer = offerCalculator.calculateOffer(cart.getLines(), everyNthProductDiscountPolicy);

        return currentOffer;
    }


    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        Offer offer = this.getCurrentOffer(customerId);

        Reservation pendingReservation = reservationService.create(Reservation.of(
                customerId,
                acceptOfferRequest,
                offer
        ));

        PaymentResponse paymentResponse = paymentGateway.registerPayment(
                PaymentRequest.of(pendingReservation.getId(), acceptOfferRequest, offer)
        );

        System.out.println(paymentResponse.getOrderId());

        reservationService.setPayUOrderId(pendingReservation.getId(), paymentResponse.getOrderId());

        return new ReservationDetail(
                pendingReservation.getId(),
                paymentResponse.getRedirectUri(),
                offer.getTotal());
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
