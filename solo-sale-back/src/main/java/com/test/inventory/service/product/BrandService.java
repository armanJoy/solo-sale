package com.test.inventory.service.product;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.dto.request.product.*;
import com.test.inventory.dto.response.product.*;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.AbstractService;
import com.test.inventory.model.product.*;
import com.test.inventory.repository.product.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BrandService extends AbstractService<Brand, BrandReqDto, BrandSearchDto> {

    private final BrandRepo brandRepo;

    public BrandService(BrandRepo brandRepo) {
        super(brandRepo);
        this.brandRepo = brandRepo;
    }

    @Override
    protected BrandResDto convertToResponseDto(Brand entity) {
        BrandResDto responseDto = new BrandResDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }

    @Override
    protected Brand convertToEntity(BrandReqDto dto) {
        return mapToEntity(dto, new Brand());
    }

    private Brand mapToEntity(BrandReqDto dto, Brand entity) {
        setNameWithValidation(dto.getName(), entity);
        return entity;
    }

    //For update
    @Override
    protected Brand convertToEntity(BrandReqDto dto, Brand entity) {
        return mapToEntity(dto, entity);
    }

    private void setNameWithValidation(String name, Brand brand) {
        Brand entity = brandRepo.findByNameIgnoreCase(name);

        if(Objects.isNull(entity) || entity.getId().equals(brand.getId())){
            brand.setName(name);
        } else if (entity.getIsActive()) {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST);
        } else {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST_IN_ARCHIVE);
            }
    }

    @Override
    public PageData search(BrandSearchDto searchDto) {
        Page<Brand> brands = brandRepo.findByNameAndIsActive((Objects
                        .nonNull(searchDto.getName()) ? searchDto.getName().trim() : ""),
                searchDto.isActive(), searchDto.getPageable());

        return PageData.builder()
                .data(brands.getContent().stream().map(this::convertToResponseDto).collect(Collectors.toList()))
                .totalPages(brands.getTotalPages())
                .totalElements(brands.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }
}