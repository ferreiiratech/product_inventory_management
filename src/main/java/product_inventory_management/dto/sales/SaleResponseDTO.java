package product_inventory_management.dto.sales;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaleResponseDTO(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal price,
        String description,
        LocalDateTime dateOfSale
) {
}
