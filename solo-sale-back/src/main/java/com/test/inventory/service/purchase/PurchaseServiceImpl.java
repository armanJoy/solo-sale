package com.test.inventory.service.purchase;

import com.test.inventory.dto.request.purchase.PurchaseReqDto;
import com.test.inventory.dto.request.purchase.PurchaseSearchDto;
import com.test.inventory.dto.request.purchase.PurchasedProductReqDto;
import com.test.inventory.dto.response.purchase.PurchaseResDto;
import com.test.inventory.dto.response.purchase.PurchasedProductResDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.product.Product;
import com.test.inventory.model.purchase.Purchase;
import com.test.inventory.model.purchase.PurchasedProduct;
import com.test.inventory.repository.purchase.PurchaseRepo;
import com.test.inventory.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    
    private final PurchaseRepo purchaseRepo;
    private final ProductService productService;

    public PurchaseServiceImpl(PurchaseRepo purchaseRepo, ProductService productService) {
        this.purchaseRepo = purchaseRepo;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<MessageResponse> savePurchase(PurchaseReqDto dto) {
        Set<Long> pIds = dto.getPurchasedProducts().stream().map(PurchasedProductReqDto::getProductId).collect(Collectors.toSet());
        List<Product> products = productService.findAllByIds(pIds);
        if(pIds.size() != products.size()){
            throw  new RuntimeException("Invalid product");
        }
        purchaseRepo.save(convertToEntity(dto, products.stream().collect(Collectors.toMap(Product::getId, Function.identity()))));

        productService.updateProductStockUponPurchase(dto.getPurchasedProducts().stream().collect(Collectors.toMap(PurchasedProductReqDto::getProductId, Function.identity())));
        return new ResponseEntity<>(new MessageResponse("Save successful"), HttpStatus.OK);
    }

    private Purchase convertToEntity(PurchaseReqDto dto, Map<Long, Product> products){
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(dto.getPurchaseDate());
        purchase.setVendor(dto.getVendor());
        purchase.getPurchasedProducts().clear();
        for (PurchasedProductReqDto purchasedProductDto : dto.getPurchasedProducts()) {
            PurchasedProduct purchasedProduct = PurchasedProduct.builder()
                    .product(products.get(purchasedProductDto.getProductId()))
                    .price(purchasedProductDto.getPrice())
                    .quantity(purchasedProductDto.getQuantity())
                    .build();
            purchase.addPurchasedProduct(purchasedProduct);
        }
        return purchase;
    }

    private PurchaseResDto convertToResponse(Purchase purchase){
        PurchaseResDto purchaseResDto = new PurchaseResDto();
        purchaseResDto.setId(purchase.getId());
        purchaseResDto.setPurchaseDate(purchase.getPurchaseDate());
        purchaseResDto.setVendor(purchase.getVendor());
        purchaseResDto.setTotalPrice(purchase.getTotalPrice());
        purchaseResDto.setPurchasedProducts(purchase.getPurchasedProducts().stream().map(this::convertPurchasedProductResponse).toList());
        return purchaseResDto;
    }

    private PurchasedProductResDto convertPurchasedProductResponse(PurchasedProduct purchasedProduct){
        return PurchasedProductResDto.builder()
                .id(purchasedProduct.getId())
                .productId(purchasedProduct.getProduct().getId())
                .productName(purchasedProduct.getProduct().getName())
                .buyingPrice(purchasedProduct.getPrice())
                .quantity(purchasedProduct.getQuantity())
                .totalProductPrice(purchasedProduct.getQuantity() * purchasedProduct.getPrice())
                .build();
    }

    @Override
    public PageData search(PurchaseSearchDto searchDto) {
        LocalDate startTime = Objects.nonNull(searchDto.getStart()) ?
                searchDto.getStart().atStartOfDay().toLocalDate() : LocalDate.EPOCH;
        LocalDate endTime = Objects.nonNull(searchDto.getEnd()) ?
                searchDto.getEnd().atStartOfDay().truncatedTo(ChronoUnit.DAYS).plus(1,
                        ChronoUnit.DAYS).minus(1, ChronoUnit.NANOS).toLocalDate() :
                LocalDate.now().plusDays(1);
        Page<Purchase> purchaseList =
                purchaseRepo.findByNameAndIsActive(Objects.nonNull(searchDto.getVendor()) ?
                        searchDto.getVendor().trim() : "", startTime, endTime, searchDto.isActive(),
                        searchDto.getPageable());
        return PageData.builder()
                .data(purchaseList.getContent().stream().map(this::convertToResponse).toList())
                .totalElements(purchaseList.getTotalElements())
                .totalPages(purchaseList.getTotalPages())
                .currentPage(purchaseList.getNumber()+1)
                .build();
    }
}