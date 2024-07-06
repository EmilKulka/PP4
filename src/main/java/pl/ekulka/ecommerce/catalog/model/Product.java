package pl.ekulka.ecommerce.catalog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "product_id")
    private String id;
    @NotNull(
            message = "Name cannot be null")
    private String name;
    @Size(
            min = 1,
            max = 200,
            message = "Description must be between 1 and 200 characters")
    @NotNull(
            message = "Description cannot be null")
    private String description;
    @Min(
            value = 0,
            message = "Price should not be less than 0"
    )
    @NotNull(
            message = "Price cannot be null")
    @Min(value = 0,
            message = "Price cannot be lower than 0")
    @Column(precision = 38, scale = 0)
    private BigDecimal price;

    public Product() {
    }

    public Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
