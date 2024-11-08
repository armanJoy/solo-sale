package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.purchase.PurchaseReqDto;
import com.test.inventory.dto.request.purchase.PurchaseSearchDto;
import com.test.inventory.dto.request.sale.SaleReqDto;
import com.test.inventory.dto.request.sale.SaleSearchDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.service.sale.SaleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = Router.SALE)
@Tag(name = ApiConstants.SALE)
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping()
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody SaleReqDto dto) {
        return saleService.saveSale(dto);
    }

    @PostMapping(value = ApiConstants.SEARCH)
    public PageData create(@RequestBody SaleSearchDto dto) {
        return saleService.search(dto);
    }

}