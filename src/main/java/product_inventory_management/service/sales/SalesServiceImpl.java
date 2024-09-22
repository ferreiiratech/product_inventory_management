package product_inventory_management.service.sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product_inventory_management.dto.sales.*;
import product_inventory_management.helper.DiscountHelper;
import product_inventory_management.model.product.ProductEntity;
import product_inventory_management.model.product.exception.ProductNotAvailableException;
import product_inventory_management.model.sales.SalesEntity;
import product_inventory_management.model.sales.exception.SaleQuantityInvalidException;
import product_inventory_management.repository.product.IProductRepository;
import product_inventory_management.repository.sales.ISalesRepository;
import product_inventory_management.service.product.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements ISalesService{
    private final ISalesRepository salesRepository;
    private final ProductServiceImpl productService;
    private final IProductRepository productRepository;
    private final DiscountHelper discountHelper;


    public SalesServiceImpl(
            ISalesRepository salesRepository,
            ProductServiceImpl productService,
            IProductRepository productRepository,
            DiscountHelper discountHelper
    ) {
        this.salesRepository = salesRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.discountHelper = discountHelper;
    }

    @Override
    @Transactional
    public SaleCreatedResponseDTO registerSale(SaleRequestDTO responseDTO) {
        if(responseDTO.quantity() <= 0){
            throw new SaleQuantityInvalidException("A quantidade de itens está inválida");
        }

        ProductEntity productFound = productService.getExistingProduct(responseDTO.productId());

        if(!productFound.isAvailable()){
            throw new ProductNotAvailableException("Produto não disponível pra venda");
        }

        if(productFound.getQuantity() < responseDTO.quantity()){
            throw new ProductNotAvailableException("Quantidade indisponível pra venda");
        }

        DiscountResultDTO discountResultDTO = discountHelper.getDiscount(productFound.getPrice(), responseDTO.quantity());

        productFound.setQuantity(productFound.getQuantity() - responseDTO.quantity());
        productRepository.save(productFound);

        SalesEntity newSale = new SalesEntity();
        newSale.setQuantity(responseDTO.quantity());
        newSale.setProductEntity(productFound);
        newSale.setPrice(discountResultDTO.discountedPrice());
        newSale.setDescription(discountResultDTO.message());
        SalesEntity registeredSale = salesRepository.save(newSale);

        return new SaleCreatedResponseDTO(
                "Venda realizada com sucesso",
                new SaleResponseDTO(
                        registeredSale.getId(),
                        registeredSale.getProductEntity().getName(),
                        registeredSale.getQuantity(),
                        registeredSale.getPrice(),
                        registeredSale.getDescription(),
                        registeredSale.getCreatedAt()
                )
        );
    }

    @Override
    public SearchSaleRegisterDTO searchAllSalesRegister(int page, int size){
        Page<SalesEntity> pageSales = salesRepository.findAll(PageRequest.of(page, size));

        List<SaleResponseDTO> saleResponseDTOList = new ArrayList<>();
        pageSales.forEach(salesEntity -> saleResponseDTOList.add(
                new SaleResponseDTO(
                    salesEntity.getId(),
                    salesEntity.getProductEntity().getName(),
                    salesEntity.getQuantity(),
                    salesEntity.getPrice(),
                    salesEntity.getDescription(),
                    salesEntity.getCreatedAt()
        )));

        return new SearchSaleRegisterDTO(
                "Busca realizada",
                saleResponseDTOList
        );
    }
}
