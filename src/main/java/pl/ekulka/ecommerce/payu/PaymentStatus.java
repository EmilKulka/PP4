package pl.ekulka.ecommerce.payu;

import java.util.List;

public class PaymentStatus {

    private List<Order> orders;

    public List<Order> getPaymentStatus() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
