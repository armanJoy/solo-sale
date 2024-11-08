package com.test.inventory.service.product;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.dto.request.product.ProductCategoryReqDto;
import com.test.inventory.dto.request.product.ProductCategorySearchDto;
import com.test.inventory.dto.response.product.ProductCategoryResDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.AbstractService;
import com.test.inventory.model.product.ProductCategory;
import com.test.inventory.repository.product.ProductCategoryRepo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService extends AbstractService<ProductCategory, ProductCategoryReqDto, ProductCategorySearchDto> {

    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategoryService(ProductCategoryRepo productCategoryRepo) {
        super(productCategoryRepo);
        this.productCategoryRepo = productCategoryRepo;
    }

    @Override
    protected ProductCategoryResDto convertToResponseDto(ProductCategory entity) {
        ProductCategoryResDto responseDto = new ProductCategoryResDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }

    @Override
    protected ProductCategory convertToEntity(ProductCategoryReqDto dto) {
        return mapToEntity(dto, new ProductCategory());
    }

    private ProductCategory mapToEntity(ProductCategoryReqDto dto, ProductCategory entity) {
        setNameWithValidation(dto.getName(), entity);
        return entity;
    }

    //For update
    @Override
    protected ProductCategory convertToEntity(ProductCategoryReqDto dto, ProductCategory entity) {
        return mapToEntity(dto, entity);
    }

    private void setNameWithValidation(String name, ProductCategory productCategory) {
        ProductCategory entity = productCategoryRepo.findByNameIgnoreCase(name);

        if(Objects.isNull(entity) || entity.getId().equals(productCategory.getId())){
            productCategory.setName(name);
        } else if (entity.getIsActive()) {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST);
        } else {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST_IN_ARCHIVE);
            }
    }

    @Override
    public PageData search(ProductCategorySearchDto searchDto) {
        Page<ProductCategory> productCategories = productCategoryRepo.findByNameAndIsActive((Objects
                        .nonNull(searchDto.getName()) ? searchDto.getName().trim() : ""),
                searchDto.isActive(), searchDto.getPageable());

        return PageData.builder()
                .data(productCategories.getContent().stream().map(this::convertToResponseDto).collect(Collectors.toList()))
                .totalPages(productCategories.getTotalPages())
                .totalElements(productCategories.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }
}