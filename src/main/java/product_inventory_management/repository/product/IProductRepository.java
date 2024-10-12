package product_inventory_management.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product_inventory_management.domain.entities.product.ProductEntity;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(long id);
    Optional<ProductEntity> findByName(String name);
    Page<ProductEntity> findAll(Pageable pageable);
}
