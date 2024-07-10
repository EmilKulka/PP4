package pl.ekulka.ecommerce.validator.product;

import pl.ekulka.ecommerce.validator.FieldValidator;

import java.math.BigDecimal;

public class PriceValidator implements FieldValidator<BigDecimal> {
    @Override
    public boolean isValid(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }
}
