package pl.ekulka.ecommerce.sales.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ReservationDetail {
    private final Long reservationId;
    private final String paymentUrl;
    private final BigDecimal total;

    @JsonCreator
    public ReservationDetail(
            @JsonProperty("reservationId") Long reservationId,
            @JsonProperty("paymentUrl") String paymentUrl,
            @JsonProperty("total") BigDecimal total){
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
        this.total = total;
    }


    public Long getReservationId() {
        return reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
