package pl.ekulka.ecommerce.payu;

public class PaymentResponse {
    PaymentStatus status;
    String orderId;
    String redirectUri;
    String extOrderId;
    public String getRedirectUri() {
        return redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getExtOrderId() {
        return extOrderId;
    }
}
