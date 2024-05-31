package pl.ekulka.ecommerce.sales.payment;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}
