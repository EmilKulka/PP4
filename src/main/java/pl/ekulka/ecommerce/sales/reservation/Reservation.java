package pl.ekulka.ecommerce.sales.reservation;

import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;

import java.math.BigDecimal;
import java.time.Instant;

public class Reservation {
    private String reservationId;
    private ClientDetails clientDetails;
    private BigDecimal total;
    private Instant paidAt;

    public Reservation(String reservationId, ClientDetails clientDetails, BigDecimal total) {

        this.reservationId = reservationId;
        this.clientDetails = clientDetails;
        this.total = total;
    }

    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer, PaymentDetails paymentDetails) {
        return new Reservation(
                reservationId,
                new ClientDetails(customerId, acceptOfferRequest.getFirstName(), acceptOfferRequest.getLastName(), acceptOfferRequest.getEmail()),
                offer.getTotal()
        );
    }

    public boolean isPending() {
        return paidAt == null;
    }

    public ClientDetails getCustomerDetails() {
        return clientDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getId() {
        return reservationId;
    }
}
