package pl.ekulka.ecommerce.validator.product;

import pl.ekulka.ecommerce.validator.FieldValidator;

public class NameValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String name) {
        return name != null;
    }
}
