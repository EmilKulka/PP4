package pl.ekulka.ecommerce.sales.offering;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.sales.cart.CartLine;
import pl.ekulka.ecommerce.sales.offer.Offer;
import pl.ekulka.ecommerce.sales.offer.OfferCalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class DiscountPolicyTest {

    @Autowired
    ProductCatalogServiceImpl productCatalogService;


    @Test
    void itShouldNotApplyDiscountWhenProductAddedToCardDidNotMatchDiscountPolicyTerms() {
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        List<CartLine> productInCart = thereIsProductAddedOnceInCart();

        Offer offer = offerCalculator.calculateOffer(productInCart, new EveryNthProductDiscountPolicy(5));

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(100));

    }

    @Test
    void itShouldApplyDiscountWhenProductsAddedToCartMatchDiscountPolicyTerms() {
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        List<CartLine> productsInCart = thereIsProductAddedFiveTimesInCart();

        Offer offer = offerCalculator.calculateOffer(productsInCart, new EveryNthProductDiscountPolicy(5));

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(400));

    }

    private List<CartLine> thereIsProductAddedOnceInCart() {
        return Arrays.asList(
                new CartLine(thereIsProductID(), 1)
        );
    }

    private List<CartLine> thereIsProductAddedFiveTimesInCart() {
        return Arrays.asList(
                new CartLine(thereIsProductID(), 5)
        );
    }

    private String thereIsProductID() {
        Product product = new Product(
                UUID.randomUUID(),
                "Example Name",
                "Example Description",
                BigDecimal.valueOf(100)
        );
        productCatalogService.addProduct(product);
        return product.getId();
    }


    private OfferCalculator thereIsOfferCalculator() {
        return new OfferCalculator(productCatalogService);
    }
}
