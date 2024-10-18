package pl.ekulka.ecommerce.infrastructure;

import pl.ekulka.ecommerce.payu.PaymentRequest;
import pl.ekulka.ecommerce.payu.PaymentResponse;
import pl.ekulka.ecommerce.payu.PayUApiClient;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUGateway implements PaymentGateway {
    private final PayUApiClient payU;
    public PayUGateway(PayUApiClient payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }

    @Override
    public PaymentResponse registerPayment(PaymentRequest request) {
        return payU.handlePaymentRequest(request);
    }
}
