package product_inventory_management.dto.sales;

public record SaleCreatedResponseDTO(
        String message,
        SaleResponseDTO data
) {
}
