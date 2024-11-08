package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.product.BrandReqDto;
import com.test.inventory.dto.request.product.BrandSearchDto;
import com.test.inventory.generic.controller.AbstractController;
import com.test.inventory.model.product.Brand;
import com.test.inventory.service.product.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(Router.BRAND)
@Tag(name = ApiConstants.BRAND)
public class BrandController extends AbstractController<Brand, BrandReqDto, BrandSearchDto> {

    public BrandController(BrandService brandService) {
        super(brandService);
    }
}