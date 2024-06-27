package pl.ekulka.ecommerce.sales.offer;

import java.math.BigDecimal;
import java.util.List;

public class Offer {
    private final List<OfferLine> lines;
    private final int itemsCount;
    private final BigDecimal total;

    public Offer(List<OfferLine> lines, BigDecimal total) {
        this.lines = lines;
        this.total = total;
        this.itemsCount = lines.size();

    }

    public int getItemsCount(){
        return itemsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OfferLine> getLines() {
        return lines;
    }
}
