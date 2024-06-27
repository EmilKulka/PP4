package pl.ekulka.ecommerce.sales.reservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.reservation.ClientDetails;

import java.math.BigDecimal;
import java.time.Instant;


public class Reservation {
    private final String reservationId;

    private final ClientDetails clientDetails;
    private final BigDecimal total;
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
