package pl.ekulka.ecommerce.sales.payment;

import pl.ekulka.ecommerce.payu.OrderCreateRequest;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

    PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest);
}
