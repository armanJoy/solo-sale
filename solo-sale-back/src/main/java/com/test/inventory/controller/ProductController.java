package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.product.ProductReqDto;
import com.test.inventory.dto.request.product.ProductSearchDto;
import com.test.inventory.generic.controller.AbstractController;
import com.test.inventory.model.product.Product;
import com.test.inventory.service.product.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(Router.PRODUCT)
@Tag(name = ApiConstants.PRODUCT)
public class ProductController extends AbstractController<Product, ProductReqDto, ProductSearchDto> {

    public ProductController(ProductService productService) {
        super(productService);
    }
}