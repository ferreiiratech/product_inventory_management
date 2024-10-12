package product_inventory_management.domain.service;

import product_inventory_management.dto.category.CategoryCreatedResponseDTO;
import product_inventory_management.dto.category.CategoryRequestDTO;

public interface ICategoryService {
    CategoryCreatedResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
}
