package pl.ekulka.ecommerce.validator;

public interface FieldValidator<T> {
    boolean isValid(T value);
}
