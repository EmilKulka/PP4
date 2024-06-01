package pl.ekulka.ecommerce.sales.offer;

import pl.ekulka.ecommerce.catalog.Product;
import pl.ekulka.ecommerce.catalog.ProductCatalog;
import pl.ekulka.ecommerce.sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferCalculator {
    ProductCatalog productCatalog;

    public Offer calculateOffer(List<CartLine> cartLines) {
        List<OfferLine> offerLines = new ArrayList<>();

        for(CartLine cartLine : cartLines) {
            offerLines.add(toOfferLine(cartLine));
        }

        return new Offer(offerLines, calculateOfferTotal(offerLines));
    }

    public OfferLine toOfferLine(CartLine cartLine) {
        Product product = productCatalog.getProductBy(cartLine.getProductId());

        BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(cartLine.getQuantity()));

        return new OfferLine(
                cartLine.getProductId(),
                product.getName(),
                product.getPrice(),
                cartLine.getQuantity(),
                lineTotal);
    }

    public BigDecimal calculateOfferTotal(List<OfferLine> offerLines) {
        BigDecimal total = BigDecimal.ZERO;

        for(OfferLine line : offerLines) {
            total = total.add(line.getTotal());
        }
        return total;
    }
}
