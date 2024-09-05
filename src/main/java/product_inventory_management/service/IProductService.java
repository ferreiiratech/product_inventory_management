package product_inventory_management.service;

import product_inventory_management.dto.*;
import product_inventory_management.model.product.ProductEntity;
import product_inventory_management.model.product.exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {
    ProductCreatedResponseDTO createProduct(ProductRequestDTO product);
    List<ProductEntity> getAllProducts();
    GetProductResponseDTO getProductById(Long id) throws ProductNotFoundException;
    ProductEntity getProductByName(String name);
    List<ProductEntity> getProductsByPriceRange(double lower, double upper);
    ProductUpdatedResponseDTO updateProduct(Long productId, ProductRequestDTO quantityReduceRequestDTO);
    ProductDeletedResponseDTO deleteProduct(Long id);
}
