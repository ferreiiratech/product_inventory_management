package product_inventory_management.dto.product;

public record ProductCreatedResponseDTO(
        String message,
        ProductResponseDTO data
) {
}
