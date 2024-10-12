package product_inventory_management.application.product;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product_inventory_management.config.exceptions.AccessDatabaseFailureException;
import product_inventory_management.domain.service.IProductService;
import product_inventory_management.dto.product.*;
import product_inventory_management.domain.entities.category.CategoryEntity;
import product_inventory_management.domain.exceptions.category.CategoryNotFoundException;
import product_inventory_management.domain.entities.product.ProductEntity;
import product_inventory_management.domain.exceptions.product.ProductNameInvalidExeception;
import product_inventory_management.domain.exceptions.product.ProductNotFoundException;
import product_inventory_management.domain.exceptions.product.ProductPriceInvalidException;
import product_inventory_management.domain.exceptions.product.ProductReportedQuantityException;
import product_inventory_management.repository.category.ICategoryRepository;
import product_inventory_management.repository.product.IProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
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

            if (productFounded.isPresent()) {
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
    public SearchProductResponseDTO searchProducts(
            Optional<String> name,
            Optional<String> categoryName,
            Optional<BigDecimal> minPrice,
            Optional<BigDecimal> maxPrice,
            Optional<Boolean> isAvailable,
            int page,
            int size
    ) {
        try {
            Page<ProductEntity> productEntityPage = productRepository.findAll(PageRequest.of(page, size));
            List<ProductEntity> productList = productEntityPage.getContent();

            if (name.isPresent()) {
                productList = productList.stream()
                        .filter(productEntity -> productEntity
                                .getName().toLowerCase()
                                .contains(name.get().toLowerCase())
                        ).toList();
            }

            if (categoryName.isPresent()) {
                productList = productList.stream()
                        .filter(productEntity -> productEntity
                                .getCategory().getName().toLowerCase()
                                .contains(categoryName.get().toLowerCase())
                        ).toList();
            }

            if (minPrice.isPresent()) {
                productList = productList.stream()
                        .filter(productEntity -> productEntity
                                .getPrice().compareTo(minPrice.get()) >= 0

                        ).toList();
            }

            if (maxPrice.isPresent()) {
                productList = productList.stream()
                        .filter(productEntity -> productEntity
                                .getPrice().compareTo(maxPrice.get()) <= 0

                        ).toList();
            }

            if(isAvailable.isPresent() && isAvailable.get()) {
                productList = productList.stream()
                        .filter(ProductEntity::isAvailable).toList();
            }

            List<ProductResponseDTO> productEntityList = new ArrayList<>();
            productList.forEach(productEntity -> productEntityList.add(
                    new ProductResponseDTO(
                        productEntity.getName(),
                        productEntity.getPrice(),
                        productEntity.getDescription(),
                        productEntity.getQuantity(),
                        productEntity.getCategory().getName()
            )));

            return new SearchProductResponseDTO(
                    "Pesquisa realizada com sucesso",
                    productEntityList
            );
        } catch (DataAccessResourceFailureException ex){
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    @Override
    public GetProductResponseDTO getProductById(Long id) {
        try {
            ProductEntity productFound = getExistingProduct(id);

            return new GetProductResponseDTO(
                    "Produto encontrado",
                    new ProductResponseDTO(
                            productFound.getName(),
                            productFound.getPrice(),
                            productFound.getDescription(),
                            productFound.getQuantity(),
                            productFound.getCategory().getName()
                    )
            );
        } catch (DataAccessResourceFailureException exception) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    @Override
    @Transactional
    public ProductUpdatedResponseDTO updateProduct(Long productId, ProductRequestDTO productUpdateRequestDTO) {
        try {
            validateProductData(productUpdateRequestDTO);

            ProductEntity productFound = getExistingProduct(productId);

            CategoryEntity categoryFound = getExistingCategory(productUpdateRequestDTO.category());

            BeanUtils.copyProperties(productUpdateRequestDTO, productFound);
            productFound.setCategory(categoryFound);

            ProductEntity productUpdated = productRepository.save(productFound);

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

    public ProductEntity getExistingProduct(Long productId) {
        try {
            Optional<ProductEntity> productFound = productRepository.findById(productId);

            if (productFound.isEmpty()) {
                throw new ProductNotFoundException("Produto não encontrado");
            }

            return productFound.get();
        } catch (DataAccessResourceFailureException exception) {
            throw new AccessDatabaseFailureException("Ocorreu um erro interno. Tente novamente mais tarde");
        }
    }

    private void validateProductData(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO.name() == null) {
            throw new ProductNameInvalidExeception("O nome do produto é inválido");
        }

        if (productRequestDTO.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductPriceInvalidException("O valor do produto não pode ser negativo");
        }

        if (productRequestDTO.quantity() < 0) {
            throw new ProductReportedQuantityException("A quantidade informada do produto não pode ser menor que zero ou nulo");
        }
    }

    private CategoryEntity getExistingCategory(String categoryName) {
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
