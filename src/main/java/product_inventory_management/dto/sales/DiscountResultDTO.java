package product_inventory_management.dto.sales;

import java.math.BigDecimal;

public record DiscountResultDTO(
        BigDecimal discountedPrice,
        String message
) {
}
