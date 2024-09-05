package product_inventory_management.dto;

public record GetProductResponseDTO(
        String message,
        ProductResponseDTO data
) {
}
