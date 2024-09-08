package product_inventory_management.service.category;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import product_inventory_management.config.exceptions.AccessDatabaseFailureException;
import product_inventory_management.dto.category.CategoryCreatedResponseDTO;
import product_inventory_management.dto.category.CategoryRequestDTO;
import product_inventory_management.dto.category.CategoryResponseDTO;
import product_inventory_management.model.category.CategoryEntity;
import product_inventory_management.model.category.exception.CategoryNameInvalidExeception;
import product_inventory_management.repository.category.ICategoryRepository;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    ICategoryRepository categoryRepository;

    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryCreatedResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        try {
            if (categoryRequestDTO.name() == null) {
                throw new CategoryNameInvalidExeception("O nome da categoria é inválido");
            }

            Optional<CategoryEntity> category = categoryRepository.findByName(categoryRequestDTO.name());

            if (category.isPresent()) {
                throw new CategoryNameInvalidExeception("O nome da categoria já existe");
            }

            CategoryEntity categoryEntity = new CategoryEntity(1L, categoryRequestDTO.name(), categoryRequestDTO.description());

            CategoryEntity categoryCreated = categoryRepository.save(categoryEntity);

            return new CategoryCreatedResponseDTO(
                    "Categoria criada com sucesso",
                    new CategoryResponseDTO(
                            categoryCreated.getName(),
                            categoryCreated.getDescription()
                    )
            );
        } catch (DataAccessResourceFailureException exception){
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }
}
