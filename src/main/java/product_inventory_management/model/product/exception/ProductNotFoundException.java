package product_inventory_management.model.product.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
