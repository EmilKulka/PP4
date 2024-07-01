package pl.ekulka.ecommerce.sales.offer;


import java.math.BigDecimal;

import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.sales.cart.CartLine;
import pl.ekulka.ecommerce.sales.productdetails.ProductDetails;
import pl.ekulka.ecommerce.sales.reservation.service.ReservationServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OfferCalculator {
    private final ProductCatalogServiceImpl productCatalogService;


    public OfferCalculator(ProductCatalogServiceImpl productCatalogService) {
        this.productCatalogService = productCatalogService;

    }

    public Offer calculateOffer(List<CartLine> cartLines) {
        List<OfferLine> offerLines = new ArrayList<>();

        for(CartLine cartLine : cartLines) {
            offerLines.add(toOfferLine(cartLine));
        }

        return new Offer(offerLines, calculateOfferTotal(offerLines));
    }

    public OfferLine toOfferLine(CartLine cartLine) {
        Product productDetails = productCatalogService.getProductById(cartLine.getProductId()).get();

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
