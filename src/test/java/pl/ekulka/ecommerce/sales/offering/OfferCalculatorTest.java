package pl.ekulka.ecommerce.sales.offering;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;
import pl.ekulka.ecommerce.sales.productdetails.InMemoryProductDetailsProvider;
import pl.ekulka.ecommerce.sales.productdetails.ProductDetails;
import pl.ekulka.ecommerce.sales.cart.CartLine;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class OfferCalculatorTest {

    @Autowired
    ProductCatalogServiceImpl productCatalogService;


    @Test
    void zeroOfferForEmptyItems() {
        OfferCalculator offerCalculator = new OfferCalculator(productCatalogService);

        Offer offer = offerCalculator.calculateOffer(Collections.emptyList());

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void itCreateOfferBasedOnCartItems() {
        Product product1 = thereIsProduct();
        Product product2 = thereIsSecondProduct();
        var product1ID = product1.getId();
        var product2ID = product2.getId();

        List<CartLine> cartItems = Arrays.asList(
                new CartLine(product1ID, 2),
                new CartLine(product2ID, 1)
        );

        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems);

        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(40.3));
    }

    private OfferCalculator thereIsOfferCalculator() {
        return new OfferCalculator(productCatalogService);
    }

    private Product thereIsProduct() {
        Product product = new Product(
                UUID.randomUUID(),
                "Test name",
                "Test description",
                BigDecimal.valueOf(10.1)
        );

        productCatalogService.addProduct(product);
        return product;
    }

    private Product thereIsSecondProduct() {
        Product product = new Product(
                UUID.randomUUID(),
                "Test name",
                "Test description",
                BigDecimal.valueOf(20.1)
        );

        productCatalogService.addProduct(product);
        return product;
    }

}
