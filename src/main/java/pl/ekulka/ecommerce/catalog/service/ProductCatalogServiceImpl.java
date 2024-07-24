package pl.ekulka.ecommerce.catalog.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.ProductRepository;

import java.util.List;
import java.util.UUID;


@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {
    private final ProductRepository repository;

    public ProductCatalogServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product createProduct(Product newProduct) {
        return repository.save(newProduct);
    }

    public Product getProductById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    public void updateProduct(Product product) {
        repository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        repository.deleteProductById(id);
    }
}
