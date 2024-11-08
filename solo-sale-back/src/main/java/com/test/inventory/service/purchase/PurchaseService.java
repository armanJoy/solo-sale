package com.test.inventory.service.purchase;

import com.test.inventory.dto.request.purchase.PurchaseReqDto;
import com.test.inventory.dto.request.purchase.PurchaseSearchDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseService {
    ResponseEntity<MessageResponse> savePurchase(PurchaseReqDto dto);

    PageData search(PurchaseSearchDto dto);
}