package pl.ekulka.ecommerce.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcPlaygroundTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void selectMyNameViaDB() {
        var currentDate = jdbcTemplate.queryForObject(
                "select now() my_date",
                String.class
        );

        assert currentDate.contains("2024");
    }


}
