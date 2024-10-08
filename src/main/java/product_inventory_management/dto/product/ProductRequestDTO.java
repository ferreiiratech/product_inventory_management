package product_inventory_management.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        BigDecimal price,
        String description,
        int quantity,
        String category
) {
}
