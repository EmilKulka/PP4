package pl.ekulka.ecommerce.sales.productdetails;

import pl.ekulka.ecommerce.catalog.Product;
import pl.ekulka.ecommerce.catalog.ProductCatalog;

import java.util.Optional;

public class ProductCatalogProductDetailsProvider implements ProductDetailsProvider {
    private  ProductCatalog productCatalog;

    public ProductCatalogProductDetailsProvider(ProductCatalog catalog) {
        this.productCatalog = catalog;
    }

    @Override
    public Optional<ProductDetails> load(String productId) {

        Product product = productCatalog.getProductBy(productId);

        if (product == null) {
            return Optional.empty();
        }

        ProductDetails productDetails = new ProductDetails(product.getId(), product.getName(), product.getPrice());
        return Optional.of(productDetails);
    }
}
