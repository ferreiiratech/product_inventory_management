package product_inventory_management.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import product_inventory_management.dto.ExceptionResponseDTO;
import product_inventory_management.model.category.exception.CategoryNotFoundException;
import product_inventory_management.model.product.exception.ProductNameInvalidExeception;
import product_inventory_management.model.product.exception.ProductNotFoundException;
import product_inventory_management.model.product.exception.ProductPriceInvalidException;
import product_inventory_management.model.product.exception.ProductReportedQuantityException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDatabaseFailureException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAccessDatabaseFailureException(AccessDatabaseFailureException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO("O valor informado deve ser numérico"));
    }

    @ExceptionHandler(ProductReportedQuantityException.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductReportedQuantityException(ProductReportedQuantityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(ProductNameInvalidExeception.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductAlreadyExistsException(ProductNameInvalidExeception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(ProductPriceInvalidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductPriceInvalidException(ProductPriceInvalidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(ex.getMessage()));
    }
}
