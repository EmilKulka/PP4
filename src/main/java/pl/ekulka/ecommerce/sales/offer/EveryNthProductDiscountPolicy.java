package pl.ekulka.ecommerce.sales.offer;

import pl.ekulka.ecommerce.sales.offer.OfferLine;

import java.math.BigDecimal;

public class EveryNthProductDiscountPolicy {
    private final int quantityThreshold;

    public EveryNthProductDiscountPolicy(int quantityThreshold) {
        this.quantityThreshold = quantityThreshold;
    }


    public OfferLine apply(OfferLine offerLine) {
        if(!isApplicable(offerLine)) {
            return offerLine;
        }

        double totalFreeProducts = Math.floor((double) offerLine.getQuantity() / (double) quantityThreshold);

        BigDecimal lineTotalAfterDiscount = offerLine
                .getUnitPrice()
                .multiply(BigDecimal.valueOf(offerLine.getQuantity()))
                .subtract(BigDecimal.valueOf(totalFreeProducts).multiply(offerLine.getUnitPrice()))
                .setScale(0);

        return new OfferLine(offerLine.getProductId(), offerLine.getName(), offerLine.getUnitPrice(), offerLine.getQuantity(), lineTotalAfterDiscount);
    }

    private boolean isApplicable(OfferLine offerLine) {
        float quantityRatio = (float) offerLine.getQuantity() / (float) quantityThreshold;
        return quantityRatio >= 1;
    }
}
