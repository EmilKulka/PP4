package pl.ekulka.ecommerce.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;
import pl.ekulka.ecommerce.catalog.storage.ProductRepository;
import pl.ekulka.validator.product.DescriptionValidator;
import pl.ekulka.validator.product.NameValidator;
import pl.ekulka.validator.product.PriceValidator;

@Configuration
public class CatalogConfig {
    @Bean
    public NameValidator nameValidator() {
        return new NameValidator();
    }

    @Bean
    public DescriptionValidator descriptionValidator() {
        return new DescriptionValidator();
    }

    @Bean
    public PriceValidator priceValidator() {
        return new PriceValidator();
    }




}
