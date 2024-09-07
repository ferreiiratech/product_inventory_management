package product_inventory_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product_inventory_management.dto.*;
import product_inventory_management.service.IProductService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCreatedResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductCreatedResponseDTO productCreated = productService.createProduct(productRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponseDTO> getOneProduct(@PathVariable Long productId)  {
        GetProductResponseDTO productFound = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(productFound);
    }

    @GetMapping("/search")
    public ResponseEntity<SearchProductResponseDTO> searchProducts(
            @RequestParam Optional<String> name,
            @RequestParam(defaultValue = "1") Optional<BigDecimal> minPrice,
            @RequestParam(defaultValue = "8000") Optional<BigDecimal> maxPrice,
            @RequestParam Optional<String> categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        SearchProductResponseDTO productEntityList = productService.getAllProducts(name, categoryName, minPrice, maxPrice, page, size);
       return ResponseEntity.status(HttpStatus.OK).body(productEntityList);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductUpdatedResponseDTO> UpdateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO quantityReduceRequestDTO){
        ProductUpdatedResponseDTO productUpdatedResponseDTO = productService.updateProduct(productId, quantityReduceRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdatedResponseDTO);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDeletedResponseDTO> deleteOneProduct(@PathVariable Long productId)  {
        ProductDeletedResponseDTO productDeletedResponseDTO = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDeletedResponseDTO);
    }
}
