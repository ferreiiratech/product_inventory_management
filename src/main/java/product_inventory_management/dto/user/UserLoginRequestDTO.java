package product_inventory_management.dto.user;

public record UserLoginRequestDTO(
        String username,
        String password
) {
}
