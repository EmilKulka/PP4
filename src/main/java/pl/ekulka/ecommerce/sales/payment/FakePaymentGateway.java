package pl.ekulka.ecommerce.sales.payment;

import java.util.UUID;

public class FakePaymentGateway implements PaymentGateway{
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        RegisterPaymentResponse response = new RegisterPaymentResponse(
                UUID.randomUUID().toString(),
                "https://fake-gateway"
        );

        return new PaymentDetails(
                response.getPaymentUrl(),
                registerPaymentRequest.getReservationId(),
                response.getPaymentId()
                );
    }
}
