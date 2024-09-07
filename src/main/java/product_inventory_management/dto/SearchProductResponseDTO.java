package product_inventory_management.dto;

import java.util.List;

public record SearchProductResponseDTO(
        String mensagem,
        List<ProductResponseDTO> data
) {
}
