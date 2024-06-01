package pl.ekulka.ecommerce.sales.offer;

import java.math.BigDecimal;
import java.util.List;

public class Offer {
    private final List<OfferLine> lines;
    private final BigDecimal total;

    public Offer(List<OfferLine> lines, BigDecimal total) {
        this.lines = lines;
        this.total = total;
    }

    public int getItemsCount(){
        return lines.size();
    }

    public BigDecimal getTotal() {
        return total;
    }
}
