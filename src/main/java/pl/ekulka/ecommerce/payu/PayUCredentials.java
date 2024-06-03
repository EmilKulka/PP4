package pl.ekulka.ecommerce.payu;

public class PayUCredentials {
    boolean sandbox;

    public static PayUCredentials(String clientId, String clientSecret, boolean sandbox) {
        return new PayUCredentials(clientId, clientSecret, sandbox);
    }

}
