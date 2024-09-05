package product_inventory_management.dto;

public record ProductCreatedResponseDTO(
        String message,
        ProductResponseDTO data
) {
}
