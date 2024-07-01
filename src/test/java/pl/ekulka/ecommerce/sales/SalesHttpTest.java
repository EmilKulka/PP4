package pl.ekulka.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;
import pl.ekulka.ecommerce.sales.offer.AcceptOfferRequest;
import pl.ekulka.ecommerce.sales.reservation.ReservationDetail;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {

    @Autowired
    TestRestTemplate http;
    @LocalServerPort
    private int port;

    @Autowired
    ProductCatalogTemp catalog;
    
    private String thereIsExampleProduct(String name, BigDecimal price) {
        var id = catalog.addProduct(name, "Example Product",price);
        return id;
    }

    @Test
    void itAllowToAcceptOffer() {
        //ARRANGE
        String productId = thereIsExampleProduct("Example Product", BigDecimal.valueOf(100));

        //ACT
        //add product to cart
        String addProductURL = String.format("http://localhost:%s/%s/%s",
                port,
                "api/add-to-cart",
                productId);


        ResponseEntity<Object> addProductResponse = http.postForEntity(addProductURL, null, Object.class);




        //accept offer
        String acceptOfferUrl = String.format(
                "http://localhost:%s/%s",
                port,
                "api/accept-offer");

        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setEmail("xyz@gmail.com")
                .setFirstName("Emil")
                .setLastName("Kulka");

        ResponseEntity<ReservationDetail> reservationDetailResponseEntity = http.postForEntity(
                acceptOfferUrl,
                acceptOfferRequest,
                ReservationDetail.class);

        //Arrange
        //-> reservationWithIdExists
        //->thereIsPaymentURLAvailable



        assertEquals(addProductResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(reservationDetailResponseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(reservationDetailResponseEntity.getBody().getReservationId());
        assertNotNull(reservationDetailResponseEntity.getBody().getPaymentUrl());
        assertEquals(BigDecimal.valueOf(100), reservationDetailResponseEntity.getBody().getTotal());
    }

}
