package product_inventory_management.dto;

public record ProductUpdatedResponseDTO(
        String message,
        ProductRequestDTO data
) {
}
