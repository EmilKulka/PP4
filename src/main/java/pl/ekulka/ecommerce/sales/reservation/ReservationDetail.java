package pl.ekulka.ecommerce.sales.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ReservationDetail {
    private final String reservationId;
    private final String paymentUrl;

    @JsonCreator
    public ReservationDetail(
            @JsonProperty("reservationId") String reservationId,
            @JsonProperty("paymentUrl") String paymentUrl) {
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
    }


    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(10.10);
    }

}
