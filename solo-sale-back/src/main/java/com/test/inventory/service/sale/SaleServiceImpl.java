package com.test.inventory.service.sale;

import com.test.inventory.dto.request.sale.SaleReqDto;
import com.test.inventory.dto.request.sale.SaleSearchDto;
import com.test.inventory.dto.request.sale.SoldProductReqDto;
import com.test.inventory.dto.response.purchase.PurchasedProductResDto;
import com.test.inventory.dto.response.sale.SaleResDto;
import com.test.inventory.dto.response.sale.SoldProductResDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.product.Product;
import com.test.inventory.model.purchase.PurchasedProduct;
import com.test.inventory.model.sale.Sale;
import com.test.inventory.model.sale.SoldProduct;
import com.test.inventory.repository.sale.SaleRepo;
import com.test.inventory.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepo saleRepo;
    private final ProductService productService;

    public SaleServiceImpl(SaleRepo saleRepo, ProductService productService) {
        this.saleRepo = saleRepo;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<MessageResponse> saveSale(SaleReqDto dto) {
        Set<Long> pIds =
                dto.getSoldProducts().stream().map(SoldProductReqDto::getProductId).collect(Collectors.toSet());
        List<Product> products = productService.findAllByIds(pIds);
        if(pIds.size() != products.size()){
            throw  new RuntimeException("Invalid product");
        }
        saleRepo.save(convertToEntity(dto, products.stream().collect(Collectors.toMap(Product::getId,
                Function.identity()))));

        productService.updateProductStockUponSale(dto.getSoldProducts().stream().collect(Collectors.toMap(SoldProductReqDto::getProductId, Function.identity())));
        return new ResponseEntity<>(new MessageResponse("Save successful"), HttpStatus.OK);
    }

    private Sale convertToEntity(SaleReqDto dto, Map<Long, Product> products){
        Sale sale = new Sale();
        sale.setSaleDate(dto.getSaleDate());
        sale.setCustomerName(dto.getCustomerName());
        sale.setCustomerPhone(dto.getCustomerPhone());
        sale.setCustomerAddress(dto.getCustomerAddress());
        sale.setDiscountPercentage(dto.getDiscountPercentage());
        sale.setDeliveryCharge(dto.getDeliveryCharge());
        sale.getSoldProducts().clear();
        for (SoldProductReqDto purchasedProductDto : dto.getSoldProducts()) {
            Product product = products.get(purchasedProductDto.getProductId());
            SoldProduct soldProduct = SoldProduct.builder()
                    .product(product)
                    .sellingPrice(product.getSellingPrice())
                    .quantity(purchasedProductDto.getQuantity())
                    .build();
            sale.addSoldProduct(soldProduct);
        }
        return sale;
    }

    private SaleResDto convertToResponse(Sale sale){
        SaleResDto saleResDto = new SaleResDto();
        saleResDto.setId(sale.getId());
        saleResDto.setSaleDate(sale.getSaleDate());
        saleResDto.setCustomerName(sale.getCustomerName());
        saleResDto.setCustomerPhone(sale.getCustomerPhone());
        saleResDto.setCustomerAddress(sale.getCustomerAddress());
        saleResDto.setDiscountPercentage(sale.getDiscountPercentage());
        saleResDto.setDeliveryCharge(sale.getDeliveryCharge());
        saleResDto.setPaid(sale.isPaid());
        saleResDto.setDelivered(sale.isDelivered());
        saleResDto.setTotalProductPrice(sale.getTotalProductPrice());
        saleResDto.setTotalPrice(sale.getTotalPrice());
        saleResDto.setSoldProducts(sale.getSoldProducts().stream().map(item->convertSoldProductResponse(item, sale)).toList());
        return saleResDto;
    }

    private SoldProductResDto convertSoldProductResponse(SoldProduct soldProduct, Sale sale){
        return SoldProductResDto.builder()
                .id(soldProduct.getId())
                .productId(soldProduct.getProduct().getId())
                .productName(soldProduct.getProduct().getName())
                .sellingPrice(soldProduct.getSellingPrice())
                .quantity(soldProduct.getQuantity())
                .totalPrice((soldProduct.getQuantity() * soldProduct.getSellingPrice()) - ((soldProduct.getQuantity() * soldProduct.getSellingPrice())* (Objects.nonNull(sale.getDiscountPercentage()) && sale.getDiscountPercentage() > 0 ? (sale.getDiscountPercentage()/100.0) : 1)))
                .build();
    }

    @Override
    public PageData search(SaleSearchDto searchDto) {
        LocalDate startTime = Objects.nonNull(searchDto.getStart()) ?
                searchDto.getStart().atStartOfDay().toLocalDate() : LocalDate.EPOCH;
        LocalDate endTime = Objects.nonNull(searchDto.getEnd()) ?
                searchDto.getEnd().atStartOfDay().truncatedTo(ChronoUnit.DAYS).plus(1,
                        ChronoUnit.DAYS).minus(1, ChronoUnit.NANOS).toLocalDate() :
                LocalDate.now().plusDays(1);
        Page<Sale> saleList = saleRepo.findByNameAndIsActive(Objects.nonNull(searchDto.getCustomerName()) ?
                        searchDto.getCustomerName().trim() : "", Objects.nonNull(searchDto.getCustomerPhone()) ?
                        searchDto.getCustomerPhone().trim() : "" ,
                startTime, endTime, searchDto.isActive(), searchDto.getPageable());
        return PageData.builder()
                .data(saleList.getContent().stream().map(this::convertToResponse).toList())
                .totalElements(saleList.getTotalElements())
                .totalPages(saleList.getTotalPages())
                .currentPage(saleList.getNumber()+1)
                .build();
    }
}