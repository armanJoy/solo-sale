package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.purchase.PurchaseReqDto;
import com.test.inventory.dto.request.purchase.PurchaseSearchDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.purchase.Purchase;
import com.test.inventory.service.purchase.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(path = Router.PURCHASE)
@Tag(name = ApiConstants.PURCHASE)
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody PurchaseReqDto dto) {
        return purchaseService.savePurchase(dto);
    }

    @PostMapping(value = ApiConstants.SEARCH)
    public PageData create(@RequestBody PurchaseSearchDto dto) {
        return purchaseService.search(dto);
    }

}