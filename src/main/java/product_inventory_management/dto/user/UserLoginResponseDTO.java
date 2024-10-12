package product_inventory_management.dto.user;

public record UserLoginResponseDTO(
        String message,
        LoginData data
) {
}
