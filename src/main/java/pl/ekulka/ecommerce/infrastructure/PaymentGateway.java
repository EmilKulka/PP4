package pl.ekulka.ecommerce.infrastructure;

import pl.ekulka.ecommerce.payu.PaymentRequest;
import pl.ekulka.ecommerce.payu.PaymentResponse;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

    PaymentResponse registerPayment(PaymentRequest registerPaymentRequest);
}
