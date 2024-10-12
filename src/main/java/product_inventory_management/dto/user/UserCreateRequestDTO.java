package product_inventory_management.dto.user;

public record UserCreateRequestDTO(
        String fullName,
        String username,
        String password,
        String userRoleType
) {
}
