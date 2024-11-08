package com.test.inventory.service.designation;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.dto.request.designation.DesignationRequestDto;
import com.test.inventory.dto.request.designation.DesignationSearchDto;
import com.test.inventory.dto.response.designation.DesignationResponseDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.AbstractService;
import com.test.inventory.model.designation.Designation;
import com.test.inventory.repository.designation.DesignationRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DesignationService extends AbstractService<Designation, DesignationRequestDto, DesignationSearchDto> {

    private final DesignationRepository designationRepository;

    public DesignationService(DesignationRepository designationRepository) {
        super(designationRepository);
        this.designationRepository = designationRepository;
    }

    public List<Designation> findDesignationByNameIn(Set<String> designationNames) {
        return designationRepository.findDesignationByNameIgnoreCaseInAndIsActiveTrue(designationNames);
    }


    @Override
    protected DesignationResponseDto convertToResponseDto(Designation entity) {
        DesignationResponseDto responseDto = new DesignationResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setName(entity.getName());
        return responseDto;
    }

    @Override
    protected Designation convertToEntity(DesignationRequestDto dto) {
        return mapToEntity(dto, new Designation());
    }

    private Designation mapToEntity(DesignationRequestDto dto, Designation entity) {
        setNameWithValidation(dto.getName(), entity);
        return entity;
    }

    //For update
    @Override
    protected Designation convertToEntity(DesignationRequestDto dto, Designation entity) {
        return mapToEntity(dto, entity);
    }

    private void setNameWithValidation(String name, Designation designation) {
        Designation entity = designationRepository.findDesignationByNameIgnoreCase(name);

        if(Objects.isNull(entity) || entity.getId().equals(designation.getId())){
            designation.setName(name);
        } else if (entity.getIsActive()) {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST);
        } else {
            throw new RuntimeException(ApplicationConstant.DESIGNATION_NAME_ALREADY_EXIST_IN_ARCHIVE);
            }
    }

    public Designation findSingle(Long designationId) {
        return designationRepository.findByIdAndIsActiveTrue(designationId)
                .orElseThrow(() -> CommonServerException.notFound(ApplicationConstant.NOT_FOUND));
    }

    public List<DesignationResponseDto> getAllDesignation() {
        return designationRepository.findAllAndIsActiveTrue().stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public PageData search(DesignationSearchDto searchDto) {
        Page<Designation> designations = designationRepository.findByDesignationNameAndIsActive((Objects
                        .nonNull(searchDto.getSearch()) ? searchDto.getSearch().trim() : ""),
                searchDto.isActive(), searchDto.getPageable());

        return PageData.builder()
                .data(designations.getContent().stream().map(this::convertToResponseDto).collect(Collectors.toList()))
                .totalPages(designations.getTotalPages())
                .totalElements(designations.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }
}