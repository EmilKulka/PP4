package pl.ekulka.ecommerce.payu;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class PayUApiClient {

    public static final String PAYU_TOKEN_URL = "%s/pl/standard/user/oauth/authorize";
    public static final String PAYU_ORDER_URL_TEMPLATE = "%s/api/v2_1/orders";
    RestTemplate http;
    private final PayUCredentials payUCredentials;

    public PayUApiClient(RestTemplate http, PayUCredentials payUCredentials) {
        this.http = http;
        this.payUCredentials = payUCredentials;
    }

    public PaymentResponse handlePaymentRequest(PaymentRequest paymentRequest) {

        HttpEntity<PaymentRequest> request = preparePaymentRequestEntity(paymentRequest);

        ResponseEntity<PaymentResponse> paymentResponseEntity = sendPaymentRequest(request);

        return paymentResponseEntity.getBody();
    }

    public String getPaymentStatus(String payUOrderId) {
        HttpEntity<Object> headers = new HttpEntity<>(createHeaders());

        ResponseEntity<PaymentStatus> paymentStatus = http.exchange(
                String.format(PAYU_ORDER_URL_TEMPLATE + "/%s", payUCredentials.getBaseUrl(), payUOrderId),
                HttpMethod.GET,
                headers,
                PaymentStatus.class
        );

        Order order = paymentStatus.getBody().getPaymentStatus().get(0);

        return order.getStatus();
    }

    private ResponseEntity<PaymentResponse> sendPaymentRequest(HttpEntity<PaymentRequest> request) {
        return http.postForEntity(
                String.format(PAYU_ORDER_URL_TEMPLATE, payUCredentials.getBaseUrl()),
                request,
                PaymentResponse.class);
    }

    private HttpEntity<PaymentRequest> preparePaymentRequestEntity(PaymentRequest paymentRequest) {
        return new HttpEntity<>(paymentRequest, createHeaders());
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));

        return headers;
    }

    private String getToken() {
        HttpEntity<String> request = prepareTokenRequestEntity();
        ResponseEntity<AccessTokenResponse> atResponse = sendTokenRequest(request);
        return atResponse.getBody().getAccessToken();
    }

    private HttpEntity<String> prepareTokenRequestEntity() {
        String body = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                payUCredentials.getClientId(),
                payUCredentials.getClientSecret()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }

    private ResponseEntity<AccessTokenResponse> sendTokenRequest(HttpEntity<String> request) {
        return http.postForEntity(
                String.format(PAYU_TOKEN_URL, payUCredentials.getBaseUrl()),
                request,
                AccessTokenResponse.class);
    }
}
