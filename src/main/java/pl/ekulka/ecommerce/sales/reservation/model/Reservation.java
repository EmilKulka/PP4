package pl.ekulka.ecommerce.sales.reservation.model;

import jakarta.persistence.*;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private ClientDetails customerId;
    @Column(precision = 38, scale = 0)
    private BigDecimal total;
    private String paymentStatus;

    private String payuOrderId;

    public Reservation() {
    }

    public Reservation(ClientDetails customerId, BigDecimal total) {
        this.customerId = customerId;
        this.total = total;
        this.paymentStatus = "PENDING";
    }

    public static Reservation of(String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer) {
        return new Reservation(
                new ClientDetails(customerId, acceptOfferRequest.getFirstName(), acceptOfferRequest.getLastName(), acceptOfferRequest.getEmail()),
                offer.getTotal()
        );
    }

    public ClientDetails getCustomerDetails() {
        return customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Long getId() {
        return id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPayuOrderId() {
        return payuOrderId;
    }

    public Reservation setPayuOrderId(String payuOrderId) {
        this.payuOrderId = payuOrderId;
        return this;
    }
}
