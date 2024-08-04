package pl.ekulka.ecommerce.sales.reservation.model;

import jakarta.persistence.*;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Reservation {
    @Id
    private String reservationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private ClientDetails customerId;
    @Column(precision =38,scale = 0)
    private BigDecimal total;
    private Instant paidAt;

    public Reservation() {
    }

    public Reservation(String reservationId, ClientDetails customerId, BigDecimal total) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.total = total;
    }

    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer) {
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
        return customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getId() {
        return reservationId;
    }
}
