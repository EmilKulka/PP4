package pl.ekulka.ecommerce.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ekulka.ecommerce.validator.product.DescriptionValidator;
import pl.ekulka.ecommerce.validator.product.NameValidator;
import pl.ekulka.ecommerce.validator.product.PriceValidator;

@Configuration
public class CatalogConfiguration {
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
