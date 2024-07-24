package pl.ekulka.ecommerce.sales;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.sales.offer.Offer;


import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalesTest {

    @Autowired
    SalesFacade sales;

    @Autowired
    ProductCatalogServiceImpl productRepository;


    @Test
    void itShowsOffer(){
        String customerId = thereIsExampleCustomer("Emil");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }



    @Test
    void itAllowsToAddProductToCart(){
        var customerId = thereIsExampleCustomer("Emil");
        var productID = thereIsProduct().getId();


        sales.addToCart(customerId, productID);

        Offer offer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
        assertEquals(1, offer.getItemsCount());

    }

    @Test
    void itAllowsToAddMultipleProductsToCart(){
        var customerId = thereIsExampleCustomer("Emil");
        var productAID = thereIsProduct().getId();
        var productBID = thereIsSecondProduct().getId();


        sales.addToCart(customerId, productAID);
        sales.addToCart(customerId, productBID);

        Offer offer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());
        assertEquals(2, offer.getItemsCount());

    }

    @Test
    void itDoesNotShareCustomersCarts(){
        var customerA = thereIsExampleCustomer("Emil");
        var customerB = thereIsExampleCustomer("XYZ");
        var productAID = thereIsProduct().getId();
        var productBID = thereIsSecondProduct().getId();


        sales.addToCart(customerA, productAID);
        sales.addToCart(customerB, productBID);

        Offer offerA = sales.getCurrentOffer(customerA);
        assertEquals(BigDecimal.valueOf(10), offerA.getTotal());

        Offer offerB = sales.getCurrentOffer(customerB);
        assertEquals(BigDecimal.valueOf(20), offerB.getTotal());

    }



    private Product thereIsProduct() {
        Product product = new Product(
                "Test name",
                "Test description",
                BigDecimal.valueOf(10)
        );

        return productRepository.createProduct(product);
    }

    private Product thereIsSecondProduct() {
        Product product = new Product(
                "Test name",
                "Test description",
                BigDecimal.valueOf(20)
        );

        productRepository.createProduct(product);
        return productRepository.createProduct(product);
    }

    @Test
    void itAllowsToRemoveProductFromCart(){

    }
}
