package product_inventory_management.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import product_inventory_management.repository.user.IUserRepository;

@Service
public class AuthorizationService implements UserDetailsService {
    private final IUserRepository userRepository;

    public AuthorizationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = userRepository.findByUsername(username);

        if(userDetails == null){
            throw new UsernameNotFoundException(username);
        }

        return userDetails;
    }
}
