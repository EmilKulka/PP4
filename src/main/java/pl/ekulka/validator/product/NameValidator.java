package pl.ekulka.validator.product;

import pl.ekulka.validator.FieldValidator;

public class NameValidator implements FieldValidator<String> {
    @Override
    public boolean isValid(String name) {
        return name != null;
    }
}
