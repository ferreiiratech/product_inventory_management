package product_inventory_management.domain.exceptions.product;

public class ProductNameInvalidExeception extends RuntimeException {
    public ProductNameInvalidExeception(String message) {
        super(message);
    }
}
