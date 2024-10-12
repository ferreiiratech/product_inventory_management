package product_inventory_management.application.user;

import org.springframework.stereotype.Service;
import product_inventory_management.domain.service.IUserService;
import product_inventory_management.repository.user.IUserRepository;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
