package pl.ekulka.ecommerce.sales.offer;

import pl.ekulka.ecommerce.sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.List;

public class OfferCalculator {

    public Offer calculate(List<CartLine> lines) {
        return new Offer(BigDecimal.valueOf(10).multiply(new BigDecimal(lines.size())),
                lines.size());
    }
}
