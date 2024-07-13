package pl.ekulka.ecommerce.sales.offer;

import java.math.BigDecimal;

public class OfferLine {
    private final String productId;
    private final String name;
    private final BigDecimal unitPrice;
    private final int quantity;
    private final BigDecimal total;

    public OfferLine(String productId, String name, BigDecimal unitPrice, int quantity, BigDecimal total) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
