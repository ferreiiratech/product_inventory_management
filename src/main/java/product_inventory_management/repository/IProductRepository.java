package product_inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product_inventory_management.model.product.ProductEntity;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByNameOrId(String name, Long id);
}
