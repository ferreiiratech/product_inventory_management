package product_inventory_management.dto.sales;

public record SaleRequestDTO(
        Long productId,
        Integer quantity
) {
}
