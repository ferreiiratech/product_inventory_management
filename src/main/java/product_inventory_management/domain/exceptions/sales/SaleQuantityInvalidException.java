package product_inventory_management.domain.exceptions.sales;

public class SaleQuantityInvalidException extends RuntimeException {
    public SaleQuantityInvalidException(String message) {
        super(message);
    }
}
