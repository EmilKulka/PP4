package pl.ekulka.ecommerce.catalog.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.ekulka.ecommerce.catalog.model.Product;
import pl.ekulka.ecommerce.catalog.storage.ProductRepository;
import pl.ekulka.ecommerce.catalog.exception.ProductNotFoundException;

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
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public Product updateProduct(Product product) {
        if(!repository.existsById(product.getId())) {
            throw new ProductNotFoundException("Product with id " + product.getId() + "not found");
        }
        return repository.save(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        if(!repository.existsById(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        repository.deleteProductById(id);
    }
}
