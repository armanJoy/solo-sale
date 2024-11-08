package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.product.ProductCategoryReqDto;
import com.test.inventory.dto.request.product.ProductCategorySearchDto;
import com.test.inventory.generic.controller.AbstractController;
import com.test.inventory.model.product.ProductCategory;
import com.test.inventory.service.product.ProductCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(Router.PRODUCT_CATEGORY)
@Tag(name = ApiConstants.PRODUCT_CATEGORY)
public class ProductCategoryController extends AbstractController<ProductCategory, ProductCategoryReqDto, ProductCategorySearchDto> {

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        super(productCategoryService);
    }
}