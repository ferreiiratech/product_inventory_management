package product_inventory_management.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(
        requestBody = @RequestBody(
            description = "Detalhes do usuário a ser criado",
            required = true,
            content = @Content(
                schema = @Schema(implementation = UserCreateRequestDTO.class),
                examples = {
                @ExampleObject(
                    name = "Exemplo de Usuário ADMIN",
                    value =
                    """
                    {
                      "fullName": "Leonardo Ferreira",
                      "username": "leoADMIN",
                      "password": "123",
                      "userRoleType": "ADMIN"
                    }
                    """
                ),
                @ExampleObject(
                    name = "Exemplo de Usuário USER",
                    value =
                    """
                    {
                      "fullName": "Leonardo Ferreira",
                      "username": "leoUSER",
                      "password": "123",
                      "userRoleType": "USER"
                    }
                    """
                )
                }
            )
        )
    )
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
