package product_inventory_management.service.sales;

import product_inventory_management.dto.sales.SaleCreatedResponseDTO;
import product_inventory_management.dto.sales.SaleRequestDTO;
import product_inventory_management.dto.sales.SearchSaleRegisterDTO;

public interface ISalesService {
    SaleCreatedResponseDTO registerSale(SaleRequestDTO responseDTO);
    SearchSaleRegisterDTO searchAllSalesRegister(int page, int size);
}
