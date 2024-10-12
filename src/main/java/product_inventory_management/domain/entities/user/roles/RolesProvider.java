package product_inventory_management.domain.entities.user.roles;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RolesProvider {
    private static final Map<UserRoleType, List<SimpleGrantedAuthority>> ROLE_AUTHORITIES = Map.of(
            UserRoleType.ADMIN, List.of(
                    new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("USER")
            ),
            UserRoleType.USER, List.of(
                    new SimpleGrantedAuthority("USER")
            )
    );

    public static List<SimpleGrantedAuthority> getRoles(UserRoleType role) {
        return ROLE_AUTHORITIES.getOrDefault(role, Collections.emptyList());
    }
}
