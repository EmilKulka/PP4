package pl.ekulka.ecommerce.payu;

import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRequest {
    String customerIp;
    String continueUrl;
    String notifyUrl;

    String merchantPosId;
    String description;
    String currencyCode;
    String totalAmount;
    String extOrderId;
    Buyer buyer;
    List<Product> products;

    public static PaymentRequest of(Long reservationId, AcceptOfferRequest acceptOfferRequest, Offer offer) {
        return new PaymentRequest()
                .setNotifyUrl("https://my.example.shop.ekulka.pl/api/order")
                .setContinueUrl("http://localhost:8888")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("[TEST] This is fake payment.")
                .setCurrencyCode("PLN")
                .setTotalAmount(offer.getTotal().toString())
                .setExtOrderId(extOrderIdGenerator(reservationId))
                .setBuyer(new Buyer()
                        .setLanguage("pl")
                        .setEmail(acceptOfferRequest.getEmail())
                        .setFirstName(acceptOfferRequest.getFirstName())
                        .setLastName(acceptOfferRequest.getLastName())
                )
                .setProducts(offer.getLines().stream()
                        .map(lineItem -> new Product(
                                        lineItem.getName(),
                                        lineItem.getUnitPrice().intValue(),
                                        lineItem.getQuantity()
                                )
                        )
                        .collect(Collectors.toList())
                );
    }

    private static String extOrderIdGenerator(Long reservationId) {
        return String.format("%s_%s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")),
                reservationId.toString());
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }

    public PaymentRequest setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getContinueUrl() {
        return continueUrl;
    }

    public PaymentRequest setContinueUrl(String continueUrl) {
        this.continueUrl = continueUrl;
        return this;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public PaymentRequest setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        return this;
    }

    public String getMerchantPosId() {
        return merchantPosId;
    }

    public PaymentRequest setMerchantPosId(String merchantPosId) {
        this.merchantPosId = merchantPosId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PaymentRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public PaymentRequest setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public PaymentRequest setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public PaymentRequest setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public PaymentRequest setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public PaymentRequest setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}



