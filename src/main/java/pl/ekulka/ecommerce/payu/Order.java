package pl.ekulka.ecommerce.payu;

public class Order {
    private String orderId;
    private String status;
    private String extOrderId;
    private String totalAmount;


    public String getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
