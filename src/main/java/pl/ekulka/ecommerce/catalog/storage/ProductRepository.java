package pl.ekulka.ecommerce.catalog.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ekulka.ecommerce.catalog.model.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
     Optional<Product>findById(UUID id);
     void deleteProductById(UUID id);

     boolean existsById(UUID id);
}
