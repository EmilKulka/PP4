package pl.ekulka.ecommerce.sales.cart;

import java.util.UUID;

public class CartLine {
    UUID productId;
    int quantity;

    public CartLine(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
