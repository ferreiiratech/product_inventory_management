package product_inventory_management.model.product.exception;

public class ProductPriceInvalidException extends RuntimeException {
    public ProductPriceInvalidException(String message) {
        super(message);
    }
}
