package product_inventory_management.controller.user;

import org.springframework.web.bind.annotation.RestController;
import product_inventory_management.application.user.UserService;
import product_inventory_management.domain.service.IUserService;

@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
}
