package pl.ekulka.ecommerce.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ekulka.ecommerce.catalog.controller.ProductCatalogController;
import pl.ekulka.ecommerce.catalog.model.ProductDto;
import pl.ekulka.ecommerce.catalog.service.ProductCatalogServiceImpl;

import java.math.BigDecimal;

@WebMvcTest(ProductCatalogController.class)
public class ProductControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductCatalogServiceImpl productCatalogService;

    ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto(
                "Example name",
                "Example description",
                BigDecimal.valueOf(100)
        );
    }

    @Test
    void shouldAllowToAddWhenValidDto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldNotAllowToAddWhenInvalidDtoAndDisplayMessages() throws Exception {
        productDto.setName(null);
        productDto.setDescription(null);
        productDto.setPrice(BigDecimal.valueOf(-1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name is mandatory"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description is mandatory"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("Price must be zero or positive"));
    }


}
