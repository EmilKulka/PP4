package pl.ekulka.ecommerce.catalog.model;

import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductMapper {
    private final ProductCatalogServiceImpl service;

    public ProductMapper(ProductCatalogServiceImpl service) {
        this.service = service;
    }

    public Product toEntity(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice()
        );
    }

    public Product toEntity(ProductDto productDto, UUID id) {
        Product product = service.getProductById(id);

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.changePrice(productDto.getPrice());
        product.setModifiedAt(LocalDateTime.now());

        return product;
    }
}
