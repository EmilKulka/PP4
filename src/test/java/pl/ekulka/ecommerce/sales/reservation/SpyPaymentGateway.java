package pl.ekulka.ecommerce.sales.reservation;

import pl.ekulka.ecommerce.payu.PaymentRequest;
import pl.ekulka.ecommerce.payu.PaymentResponse;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.infrastructure.PaymentGateway;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;

import java.util.UUID;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;
    public Integer getRequestCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails(
                "http://spy-gateway",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    @Override
    public PaymentResponse registerPayment(PaymentRequest registerPaymentRequest) {
        return null;
    }
}
