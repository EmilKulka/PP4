package pl.ekulka.ecommerce.validator.product;

import pl.ekulka.ecommerce.validator.FieldValidator;

public class DescriptionValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String description) {
        return description != null && !description.isEmpty() && description.length() <= 200;
    }

}
