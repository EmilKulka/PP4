package pl.ekulka.ecommerce.sales.ui;

import org.springframework.web.bind.annotation.*;
import pl.ekulka.ecommerce.sales.SalesFacade;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.reservation.ReservationDetail;

import java.util.UUID;

@RestController
public class SalesController {
    private final SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable(name = "productId") UUID productId) {
        String customerId = getCurrentCustomerId();
        sales.addToCart(customerId, productId);
    };

    @PostMapping("/api/accept-offer")
    ReservationDetail acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        String customerId = getCurrentCustomerId();
        ReservationDetail reservationDetail = sales.acceptOffer(customerId, acceptOfferRequest);
        return reservationDetail;
    }

    private String getCurrentCustomerId(){
        return "1";
    }
}