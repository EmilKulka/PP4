package pl.ekulka.ecommerce.sales.offer;


import java.math.BigDecimal;
import pl.ekulka.ecommerce.sales.cart.CartLine;
import pl.ekulka.ecommerce.sales.productdetails.ProductDetails;
import pl.ekulka.ecommerce.sales.productdetails.ProductDetailsProvider;
import java.util.ArrayList;
import java.util.List;

public class OfferCalculator {
    ProductDetailsProvider productDetailsProvider;


    public OfferCalculator(ProductDetailsProvider productDetailsProvider) {
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer calculateOffer(List<CartLine> cartLines) {
        List<OfferLine> offerLines = new ArrayList<>();

        for(CartLine cartLine : cartLines) {
            offerLines.add(toOfferLine(cartLine));
        }

        return new Offer(offerLines, calculateOfferTotal(offerLines));
    }

    public OfferLine toOfferLine(CartLine cartLine) {
        ProductDetails productDetails = productDetailsProvider.load(cartLine.getProductId()).get();

        BigDecimal lineTotal = productDetails.getPrice().multiply(BigDecimal.valueOf(cartLine.getQuantity()));

        return new OfferLine(
                cartLine.getProductId(),
                productDetails.getName(),
                productDetails.getPrice(),
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
