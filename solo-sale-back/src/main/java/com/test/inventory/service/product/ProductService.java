package com.test.inventory.service.product;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.dto.request.product.ProductReqDto;
import com.test.inventory.dto.request.product.ProductSearchDto;
import com.test.inventory.dto.request.purchase.PurchasedProductReqDto;
import com.test.inventory.dto.request.sale.SoldProductReqDto;
import com.test.inventory.dto.response.product.ProductResDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.AbstractService;
import com.test.inventory.model.product.Product;
import com.test.inventory.model.purchase.PurchasedProduct;
import com.test.inventory.repository.product.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<Product, ProductReqDto, ProductSearchDto> {

    private final ProductRepo productRepo;
    private final BrandService brandService;
    private final ProductCategoryService productCategoryService;

    public ProductService(ProductRepo productRepo, BrandService brandService, ProductCategoryService productCategoryService) {
        super(productRepo);
        this.productRepo = productRepo;
        this.brandService = brandService;
        this.productCategoryService = productCategoryService;
    }

    @Override
    protected ProductResDto convertToResponseDto(Product entity) {
        ProductResDto responseDto = new ProductResDto();
        responseDto.setId(entity.getId());
        responseDto.setProductName(entity.getName());
        responseDto.setMrp(entity.getMrp());
        responseDto.setSellingPrice(entity.getSellingPrice());
        responseDto.setStock(entity.getStock());
        responseDto.setBrandId(entity.getBrand().getId());
        responseDto.setBrand(entity.getBrand().getName());
        responseDto.setCategoryId(entity.getCategory().getId());
        responseDto.setCategory(entity.getCategory().getName());
        return responseDto;
    }

    @Override
    protected Product convertToEntity(ProductReqDto dto) {
        return mapToEntity(dto, new Product());
    }

    private Product mapToEntity(ProductReqDto dto, Product entity) {
        setNameWithValidation(dto.getProductName(), entity);
        entity.setBrand(brandService.findById(dto.getBrandId()));
        entity.setCategory(productCategoryService.findById(dto.getCategoryId()));
        entity.setName(dto.getProductName());
        entity.setMrp(dto.getMrp());
        entity.setSellingPrice(dto.getSellingPrice());
        return entity;
    }

    //For update
    @Override
    protected Product convertToEntity(ProductReqDto dto, Product entity) {
        return mapToEntity(dto, entity);
    }

    private void setNameWithValidation(String name, Product product) {
        Product entity = productRepo.findByNameIgnoreCase(name);

        if(Objects.isNull(entity) || entity.getId().equals(product.getId())){
            product.setName(name);
        } else if (entity.getIsActive()) {
            throw new RuntimeException("Product already exist");
        } else {
            throw new RuntimeException("Product already exist");
            }
    }

    @Override
    public PageData search(ProductSearchDto searchDto) {
        Page<Product> products = productRepo.findByNameAndIsActive((Objects.nonNull(searchDto.getName()) ? searchDto.getName().trim() : ""), searchDto.getCategoryId(), searchDto.getBrandId(), searchDto.getStockNumber(), searchDto.isActive(), searchDto.getPageable());

        return PageData.builder()
                .data(products.getContent().stream().map(this::convertToResponseDto).collect(Collectors.toList()))
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }

    public void updateProductStockUponPurchase(Map<Long,PurchasedProductReqDto> purchasedProducts){
        List<Product> products = findAllByIds(purchasedProducts.keySet());
        for (Product product : products) {
            product.addToStock(purchasedProducts.get(product.getId()).getQuantity());
        }
        productRepo.saveAll(products);
    }

    public void updateProductStockUponSale(Map<Long, SoldProductReqDto> soldProducts){
        List<Product> products = findAllByIds(soldProducts.keySet());
        for (Product product : products) {
            product.reduceToStock(soldProducts.get(product.getId()).getQuantity());
        }
        productRepo.saveAll(products);
    }
}