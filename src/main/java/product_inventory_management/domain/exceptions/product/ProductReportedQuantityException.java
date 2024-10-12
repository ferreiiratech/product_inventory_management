package product_inventory_management.domain.exceptions.product;

public class ProductReportedQuantityException extends RuntimeException {
    public ProductReportedQuantityException(String message) {
        super(message);
    }
}
