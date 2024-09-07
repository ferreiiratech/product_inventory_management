package product_inventory_management.service;

import org.springframework.data.domain.Page;
import product_inventory_management.dto.*;
import product_inventory_management.model.product.ProductEntity;
import product_inventory_management.model.product.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductCreatedResponseDTO createProduct(ProductRequestDTO product);
    GetProductResponseDTO getProductById(Long id) throws ProductNotFoundException;
    ProductUpdatedResponseDTO updateProduct(Long productId, ProductRequestDTO quantityReduceRequestDTO);
    ProductDeletedResponseDTO deleteProduct(Long id);
    SearchProductResponseDTO getAllProducts(
            Optional<String> name,
            Optional<String> categoryName,
            Optional<BigDecimal> minPrice,
            Optional<BigDecimal> maxPrice,
            int page,
            int size
    );
}
