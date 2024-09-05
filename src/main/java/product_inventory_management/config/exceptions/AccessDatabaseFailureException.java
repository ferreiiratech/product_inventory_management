package product_inventory_management.config.exceptions;

public class AccessDatabaseFailureException extends RuntimeException{
    public AccessDatabaseFailureException(String message){
        super(message);
    }
}
