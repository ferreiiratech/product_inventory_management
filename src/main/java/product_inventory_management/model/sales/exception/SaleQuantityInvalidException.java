package product_inventory_management.model.sales.exception;

public class SaleQuantityInvalidException extends RuntimeException {
    public SaleQuantityInvalidException(String message) {
        super(message);
    }
}
