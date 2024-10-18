package pl.ekulka.ecommerce.sales.payment;

public class PaymentDetails {
    private final String paymentUrl;
    private final String reservationId;
    private final String paymentId;

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentDetails(String url, String reservationId, String paymentId) {
        this.paymentUrl = url;
        this.reservationId = reservationId;
        this.paymentId = paymentId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
