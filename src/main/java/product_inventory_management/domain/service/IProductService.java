package product_inventory_management.domain.service;

import product_inventory_management.dto.product.*;

import java.math.BigDecimal;
import java.util.Optional;

public interface IProductService {
    ProductCreatedResponseDTO createProduct(ProductRequestDTO product);
    GetProductResponseDTO getProductById(Long id);
    ProductUpdatedResponseDTO updateProduct(Long productId, ProductRequestDTO quantityReduceRequestDTO);
    ProductDeletedResponseDTO deleteProduct(Long id);
    SearchProductResponseDTO searchProducts(
            Optional<String> name,
            Optional<String> categoryName,
            Optional<BigDecimal> minPrice,
            Optional<BigDecimal> maxPrice,
            Optional<Boolean> isAvailable,
            int page,
            int size
    );
}
