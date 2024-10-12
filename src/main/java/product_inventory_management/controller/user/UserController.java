package product_inventory_management.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product_inventory_management.domain.service.IUserService;
import product_inventory_management.dto.user.UserCreateRequestDTO;
import product_inventory_management.dto.user.UserCreatedResponseDTO;
import product_inventory_management.dto.user.UserLoginRequestDTO;
import product_inventory_management.dto.user.UserLoginResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserCreatedResponseDTO> register(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        UserCreatedResponseDTO userCreatedResponseDTO = userService.registerUser(userCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreatedResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO loginRequestDTO){
        UserLoginResponseDTO userLoginResponseDTO = userService.loginUser(loginRequestDTO.username(), loginRequestDTO.password());
        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponseDTO);
    }
}
