package product_inventory_management.domain.service;

import product_inventory_management.dto.user.UserCreateRequestDTO;
import product_inventory_management.dto.user.UserCreatedResponseDTO;
import product_inventory_management.dto.user.UserLoginResponseDTO;

public interface IUserService {
    UserCreatedResponseDTO registerUser(UserCreateRequestDTO userCreateRequestDTO);
    UserLoginResponseDTO loginUser(String email, String password);
}
