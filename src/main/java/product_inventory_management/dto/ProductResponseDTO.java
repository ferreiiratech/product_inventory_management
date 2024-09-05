package product_inventory_management.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String name,
        BigDecimal price,
        String description,
        int quantity,
        String category
) {
}
