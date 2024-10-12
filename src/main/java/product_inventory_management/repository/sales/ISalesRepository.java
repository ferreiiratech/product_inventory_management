package product_inventory_management.repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product_inventory_management.domain.entities.sales.SalesEntity;

@Repository
public interface ISalesRepository extends JpaRepository<SalesEntity, Long> {

}
