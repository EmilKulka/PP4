package pl.ekulka.ecommerce.infrastructure;

import pl.ekulka.ecommerce.infrastructure.PaymentGateway;
import pl.ekulka.ecommerce.payu.OrderCreateRequest;
import pl.ekulka.ecommerce.sales.payment.PaymentDetails;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.ekulka.ecommerce.sales.payment.RegisterPaymentResponse;

import java.util.UUID;

public class FakePaymentGateway implements PaymentGateway {
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
