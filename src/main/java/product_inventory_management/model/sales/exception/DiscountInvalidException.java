package product_inventory_management.model.sales.exception;

public class DiscountInvalidException extends RuntimeException {
    public DiscountInvalidException(String message) {
        super(message);
    }
}
