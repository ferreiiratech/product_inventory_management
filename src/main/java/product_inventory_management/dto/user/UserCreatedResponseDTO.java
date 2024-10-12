package product_inventory_management.dto.user;

public record UserCreatedResponseDTO(
        String fullName,
        String username,
        String role
) {
}
