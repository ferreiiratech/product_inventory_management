package product_inventory_management.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product_inventory_management.config.exceptions.AccessDatabaseFailureException;
import product_inventory_management.dto.*;
import product_inventory_management.model.category.CategoryEntity;
import product_inventory_management.model.category.exception.CategoryNotFoundException;
import product_inventory_management.model.product.ProductEntity;
import product_inventory_management.model.product.exception.ProductNameInvalidExeception;
import product_inventory_management.model.product.exception.ProductNotFoundException;
import product_inventory_management.model.product.exception.ProductPriceInvalidException;
import product_inventory_management.model.product.exception.ProductReportedQuantityException;
import product_inventory_management.repository.ICategoryRepository;
import product_inventory_management.repository.IProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductCreatedResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        try {
            validateProductData(productRequestDTO);

            Optional<ProductEntity> productFounded = productRepository.findByName(productRequestDTO.name());

            if(productFounded.isPresent()) {
                throw new ProductNameInvalidExeception("Já existe um produto com o nome informado.");
            }

            CategoryEntity categoryFound = getExistingCategory(productRequestDTO.category());

            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(productRequestDTO, productEntity);
            productEntity.setCategory(categoryFound);
            ProductEntity productCreated = productRepository.save(productEntity);

            return new ProductCreatedResponseDTO(
                    "Produto criado com sucesso",
                    new ProductResponseDTO(
                            productCreated.getName(),
                            productCreated.getPrice(),
                            productCreated.getDescription(),
                            productCreated.getQuantity(),
                            productCreated.getCategory().getName()
                    )
            );
        } catch (DataAccessResourceFailureException ex) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return List.of();
    }

    @Override
    public GetProductResponseDTO getProductById(Long id) {
        try {
            Optional<ProductEntity> productFound = productRepository.findById(id);

            if (productFound.isEmpty()) {
                throw new ProductNotFoundException("Produto não encontrado");
            }

            return new GetProductResponseDTO(
                    "Produto encontrado",
                    new ProductResponseDTO(
                            productFound.get().getName(),
                            productFound.get().getPrice(),
                            productFound.get().getDescription(),
                            productFound.get().getQuantity(),
                            productFound.get().getCategory().getName()
                    )
            );
        } catch (DataAccessResourceFailureException exception){
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    @Override
    public ProductEntity getProductByName(String name) {
        return null;
    }

    @Override
    public List<ProductEntity> getProductsByPriceRange(double lower, double upper) {
        return List.of();
    }

    @Override
    @Transactional
    public ProductUpdatedResponseDTO updateProduct(Long productId, ProductRequestDTO productUpdateRequestDTO){
        try {
            validateProductData(productUpdateRequestDTO);

            Optional<ProductEntity> productFound = productRepository.findById(productId);

            if (productFound.isEmpty()) {
                throw new ProductNotFoundException("Produto não encontrado");
            }

            CategoryEntity categoryFound = getExistingCategory(productUpdateRequestDTO.category());

            BeanUtils.copyProperties(productUpdateRequestDTO, productFound.get());
            productFound.get().setCategory(categoryFound);

            ProductEntity productUpdated = productRepository.save(productFound.get());

            return new ProductUpdatedResponseDTO(
                    "Produto atualizado com sucesso",
                    new ProductRequestDTO(
                            productUpdated.getName(),
                            productUpdated.getPrice(),
                            productUpdated.getDescription(),
                            productUpdated.getQuantity(),
                            productUpdated.getCategory().getName()
                    )
            );
        } catch (DataAccessResourceFailureException exception) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    @Override
    @Transactional
    public ProductDeletedResponseDTO deleteProduct(Long id) {
        try {
            Optional<ProductEntity> productEntity = productRepository.findById(id);

            if (productEntity.isEmpty()) {
                throw new ProductNotFoundException("Produto não encontrado");
            }

            productRepository.delete(productEntity.get());

            return new ProductDeletedResponseDTO(
                    "Produto deletado com sucesso"
            );
        } catch (DataAccessResourceFailureException exception) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    private void validateProductData(ProductRequestDTO productRequestDTO){
        if(productRequestDTO.name() == null){
            throw new ProductNameInvalidExeception("O nome do produto é inválido");
        }

        if(productRequestDTO.price().compareTo(BigDecimal.ZERO) < 0){
            throw new ProductPriceInvalidException("O valor do produto não pode ser negativo");
        }

        if(productRequestDTO.quantity() < 0){
            throw new ProductReportedQuantityException("A quantidade informada do produto não pode ser menor que zero ou nulo");
        }
    }

    private CategoryEntity getExistingCategory(String categoryName){
        try {
            Optional<CategoryEntity> categoryFound = categoryRepository.findByName(categoryName);

            if (categoryFound.isEmpty()) {
                throw new CategoryNotFoundException("Categoria '" + categoryName + "' não encontrada");
            }

            return categoryFound.get();
        } catch (DataAccessResourceFailureException exception) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

}
