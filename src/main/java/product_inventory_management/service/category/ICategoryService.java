package product_inventory_management.service.category;

import product_inventory_management.dto.category.CategoryCreatedResponseDTO;
import product_inventory_management.dto.category.CategoryRequestDTO;
import product_inventory_management.dto.category.CategoryResponseDTO;

public interface ICategoryService {
    CategoryCreatedResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
}
