package product_inventory_management.dto.product;

public record ProductUpdatedResponseDTO(
        String message,
        ProductRequestDTO data
) {
}
