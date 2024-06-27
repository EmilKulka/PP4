package pl.ekulka.ecommerce.catalog.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ekulka.ecommerce.catalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
