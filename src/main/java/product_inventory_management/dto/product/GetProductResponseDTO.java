package product_inventory_management.dto.product;

public record GetProductResponseDTO(
        String message,
        ProductResponseDTO data
) {
}
