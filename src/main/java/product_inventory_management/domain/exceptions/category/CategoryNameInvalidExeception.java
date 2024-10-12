package product_inventory_management.domain.exceptions.category;

public class CategoryNameInvalidExeception extends RuntimeException {
    public CategoryNameInvalidExeception(String message) {
        super(message);
    }
}
