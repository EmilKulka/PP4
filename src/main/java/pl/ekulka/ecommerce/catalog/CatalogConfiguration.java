package pl.ekulka.ecommerce.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ekulka.ecommerce.catalog.model.ProductMapper;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

@Configuration
public class CatalogConfiguration {

    private final ProductCatalogServiceImpl productCatalogService;

    public CatalogConfiguration(ProductCatalogServiceImpl productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @Bean
    ProductMapper productMapper() {
        return new ProductMapper(productCatalogService);
    }
}
