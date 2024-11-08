package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.designation.DesignationRequestDto;
import com.test.inventory.dto.request.designation.DesignationSearchDto;
import com.test.inventory.generic.controller.AbstractController;
import com.test.inventory.model.designation.Designation;
import com.test.inventory.service.designation.DesignationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(Router.DESIGNATION)
@Tag(name = ApiConstants.DESIGNATION)
public class DesignationController extends AbstractController<Designation, DesignationRequestDto, DesignationSearchDto> {

    public DesignationController(DesignationService designationService) {
        super(designationService);
    }
}