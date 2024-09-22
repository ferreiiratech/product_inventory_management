package product_inventory_management.dto.sales;

import java.util.List;

public record SearchSaleRegisterDTO(
        String message,
        List<SaleResponseDTO> data
) {
}
