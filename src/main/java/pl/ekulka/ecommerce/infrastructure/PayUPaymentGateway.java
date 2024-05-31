package pl.ekulka.ecommerce.infrastructure;

import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.payment.PaymentGateway;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}
