package pl.ekulka.validator;

public interface FieldValidator<T> {
    boolean isValid(T value);
}
