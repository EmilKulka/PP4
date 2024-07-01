package pl.ekulka.ecommerce.catalog.service;

import org.springframework.stereotype.Service;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.ProductRepository;
import pl.ekulka.validator.product.DescriptionValidator;
import pl.ekulka.validator.product.NameValidator;
import pl.ekulka.validator.product.PriceValidator;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService{
    private final ProductRepository repository;
    private final NameValidator nameValidator;
    private final DescriptionValidator descriptionValidator;
    private final PriceValidator priceValidator;

    public ProductCatalogServiceImpl(ProductRepository repository, NameValidator nameValidator, DescriptionValidator descriptionValidator, PriceValidator priceValidator) {
        this.repository = repository;
        this.nameValidator = nameValidator;
        this.descriptionValidator = descriptionValidator;
        this.priceValidator = priceValidator;
    }

    public List<Product> allProducts() {
        return repository.findAll();
    }

    public void addProduct(Product newProduct) {
        if (!nameValidator.isValid(newProduct.getName())) {
            throw new IllegalStateException(
                    "Name cannot be null"
            );
        }
        if (!descriptionValidator.isValid(newProduct.getDescription())) {
            throw new IllegalStateException(
                    "Description must be between 1 and 200 characters"
            );
        }
        if (!priceValidator.isValid(newProduct.getPrice())) {
            throw new IllegalStateException(
                    "Price cannot be lower than 0"
            );
        }
        repository.save(newProduct);
    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found")));
    }


}
