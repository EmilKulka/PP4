package pl.ekulka.ecommerce.sales.payment;

import pl.ekulka.ecommerce.payu.OrderCreateRequest;

import java.util.UUID;

public class FakePaymentGateway implements PaymentGateway{
    @Override
    public PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest) {
        return null;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        RegisterPaymentResponse response = new RegisterPaymentResponse(
                UUID.randomUUID().toString(),
                "https://www.kapitan.pl/"
        );

        return new PaymentDetails(
                response.getPaymentUrl(),
                registerPaymentRequest.getReservationId(),
                response.getPaymentId()
                );
    }
}
