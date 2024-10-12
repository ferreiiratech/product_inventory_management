package product_inventory_management.domain.exceptions.product;

public class ProductPriceInvalidException extends RuntimeException {
    public ProductPriceInvalidException(String message) {
        super(message);
    }
}
