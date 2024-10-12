package product_inventory_management.domain.entities.user.roles;

public enum UserRoleType {
    ADMIN("ADMIN"),
    USER("USER");

    private final String userRoleType;

    UserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserRoleType() {
        return userRoleType;
    }
}
