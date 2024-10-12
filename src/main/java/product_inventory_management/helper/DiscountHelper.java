package product_inventory_management.helper;

import org.springframework.stereotype.Component;
import product_inventory_management.dto.sales.DiscountResultDTO;
import product_inventory_management.domain.exceptions.sales.DiscountInvalidException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Component
public class DiscountHelper {
    private final TreeMap<Integer, BigDecimal> discountMap;

    public DiscountHelper() {
        discountMap = new TreeMap<>();
        discountMap.put(10, new BigDecimal("0.95"));
        discountMap.put(20, new BigDecimal("0.90"));
    }

    public DiscountResultDTO getDiscount(BigDecimal originalPrice, int quantity) {

        if(originalPrice.compareTo(BigDecimal.ZERO) == 0){
            throw new DiscountInvalidException("Valor originalPrice invalido");
        }

        Map.Entry<Integer, BigDecimal> entry = discountMap.floorEntry(quantity);
        BigDecimal discount = (entry != null) ? entry.getValue() : BigDecimal.ONE;
        BigDecimal discountedPrice = originalPrice.multiply(discount);

        String discountMessage = generateDiscountMessage(entry);

        return new DiscountResultDTO(discountedPrice, discountMessage);
    }

    private String generateDiscountMessage(Map.Entry<Integer, BigDecimal> entry) {
        if (entry == null) {
            return "Nenhum desconto foi aplicado";
        }

        BigDecimal discountPercentage = BigDecimal.ONE.subtract(entry.getValue()).multiply(new BigDecimal("100"));
        return String.format("Foi aplicado %.0f%% de desconto", discountPercentage);
    }
}
