package product_inventory_management.domain.exceptions.sales;

public class DiscountInvalidException extends RuntimeException {
    public DiscountInvalidException(String message) {
        super(message);
    }
}
