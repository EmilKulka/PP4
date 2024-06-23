package pl.ekulka.ecommerce.sales.payment;

import pl.ekulka.ecommerce.payu.Buyer;
import pl.ekulka.ecommerce.payu.OrderCreateRequest;
import pl.ekulka.ecommerce.payu.OrderCreateResponse;
import pl.ekulka.ecommerce.payu.PayU;

import java.util.UUID;

public class PayUGateway implements PaymentGateway{
    private final PayU payU;
    public PayUGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }

    @Override
    public PaymentDetails registerPayment(OrderCreateRequest request) {
        OrderCreateResponse response = payU.handle(request);
        return new PaymentDetails(
                response.getRedirectUri(),
                request.getExtOrderId(),
                UUID.randomUUID().toString()
        );
    }
}
