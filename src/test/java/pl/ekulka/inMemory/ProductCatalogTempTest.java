//package pl.ekulka.ecommerce.catalog;
//
//import org.junit.jupiter.api.Test;
//import pl.ekulka.ecommerce.catalog.model.Product;
//import pl.ekulka.ecommerce.catalog.service.ProductCatalogTemp;
//import pl.ekulka.ecommerce.catalog.storage.ArrayListProductStorage;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//public class ProductCatalogTempTest {
//
//    @Test
//    void itAllowsListingProducts() {
//        ProductCatalogTemp catalog = thereIsProductCatalog();
//
//        List<Product> products = catalog.allProducts();
//
//        assert products.isEmpty();
//    }
//
//    private ProductCatalogTemp thereIsProductCatalog() {
//        return new ProductCatalogTemp(new ArrayListProductStorage());
//    }
//
//    @Test
//    void itAllowsToAddProduct() {
//        ProductCatalogTemp catalog = thereIsProductCatalog();
//
//        catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));
//
//        List<Product> allProducts = catalog.allProducts();
//
//        assertThat(allProducts)
//                .hasSize(1);
//    }
//
//    @Test
//    void itLoadSingleProductById() {
//        ProductCatalogTemp catalog = thereIsProductCatalog();
//        String id = catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));
//
//        Product loaded = catalog.getProductBy(id);
//
//        assertThat(loaded.getId())
//                .isEqualTo(id);
//    }
//
//    @Test void itAllowsToChangePrice() {
//        ProductCatalogTemp catalog = thereIsProductCatalog();
//        String id = catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(2));
//
//        catalog.changePrice(id, BigDecimal.valueOf(10.10));
//
//        Product loaded = catalog.getProductBy(id);
//
//        assertThat(loaded.getPrice())
//                .isEqualTo(BigDecimal.valueOf(10.10));
//    }
//}
