package product_inventory_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductInventoryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryManagementApplication.class, args);
    }

}
