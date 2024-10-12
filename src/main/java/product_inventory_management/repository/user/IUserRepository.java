package product_inventory_management.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import product_inventory_management.domain.entities.user.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    UserDetails findByUsername(String username);
}
