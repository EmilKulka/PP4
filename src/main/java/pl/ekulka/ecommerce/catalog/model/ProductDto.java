package pl.ekulka.ecommerce.catalog.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price must be zero or positive")
    @Column(precision = 38, scale = 0)
    private BigDecimal price;

    public ProductDto(String exampleName, String exampleDescription, BigDecimal price) {
        this.name = exampleName;
        this.description = exampleDescription;
        this.price = price;
    }

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
