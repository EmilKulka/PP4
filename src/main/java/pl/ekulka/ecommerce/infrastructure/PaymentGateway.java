package pl.ekulka.ecommerce.infrastructure;

import pl.ekulka.ecommerce.payu.OrderCreateRequest;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

    PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest);
}
