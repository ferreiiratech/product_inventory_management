package product_inventory_management.service.category;

import product_inventory_management.dto.category.CategoryCreatedResponseDTO;
import product_inventory_management.dto.category.CategoryRequestDTO;

public interface ICategoryService {
    CategoryCreatedResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
}
