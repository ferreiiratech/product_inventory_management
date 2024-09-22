package product_inventory_management.controller.sales;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product_inventory_management.dto.sales.SaleCreatedResponseDTO;
import product_inventory_management.dto.sales.SaleRequestDTO;
import product_inventory_management.dto.sales.SearchSaleRegisterDTO;
import product_inventory_management.service.sales.ISalesService;

@RestController
@RequestMapping("/sale")
public class SalesController {
    private final ISalesService salesService;

    SalesController(ISalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/register")
    public ResponseEntity<SaleCreatedResponseDTO> registerSale(@RequestBody SaleRequestDTO saleRequestDTO){
        SaleCreatedResponseDTO responseDTO = salesService.registerSale(saleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<SearchSaleRegisterDTO> searchSale(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        SearchSaleRegisterDTO searchSaleRegisterDTO = salesService.searchAllSalesRegister(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(searchSaleRegisterDTO);
    }

}
