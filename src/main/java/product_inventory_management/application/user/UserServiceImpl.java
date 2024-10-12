package product_inventory_management.application.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import product_inventory_management.config.security.JwtTokenProvider;
import product_inventory_management.domain.entities.user.UserEntity;
import product_inventory_management.domain.entities.user.roles.UserRoleType;
import product_inventory_management.domain.exceptions.user.UserAlreadyExistsException;
import product_inventory_management.domain.service.IUserService;
import product_inventory_management.dto.user.LoginData;
import product_inventory_management.dto.user.UserCreateRequestDTO;
import product_inventory_management.dto.user.UserCreatedResponseDTO;
import product_inventory_management.dto.user.UserLoginResponseDTO;
import product_inventory_management.repository.user.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserCreatedResponseDTO registerUser(UserCreateRequestDTO userCreateRequestDTO) {
        UserDetails userFound = userRepository.findByUsername(userCreateRequestDTO.username());

        if (userFound != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(userCreateRequestDTO.fullName());
        userEntity.setUsername(userCreateRequestDTO.username());
        userEntity.setPassword(passwordEncoder.encode(userCreateRequestDTO.password()));
        userEntity.setUserRoleType(UserRoleType.valueOf(userCreateRequestDTO.userRoleType()));

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserCreatedResponseDTO(
                savedUserEntity.getFullName(),
                savedUserEntity.getUsername(),
                savedUserEntity.getUserRoleType().name()
        );
    }

    @Override
    public UserLoginResponseDTO loginUser(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(userDetails.getUsername());


        return new UserLoginResponseDTO(
                "Login with successfully",
                new LoginData(token)
        );
    }
}
