package product_inventory_management.controller.category;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product_inventory_management.dto.category.CategoryCreatedResponseDTO;
import product_inventory_management.dto.category.CategoryRequestDTO;
import product_inventory_management.domain.service.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/create")
    public ResponseEntity<CategoryCreatedResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryCreatedResponseDTO categoryCreatedResponseDTO = categoryService.createCategory(categoryRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreatedResponseDTO);
    }
}
