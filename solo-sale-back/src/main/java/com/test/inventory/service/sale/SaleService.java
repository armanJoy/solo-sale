package com.test.inventory.service.sale;

import com.test.inventory.dto.request.sale.SaleReqDto;
import com.test.inventory.dto.request.sale.SaleSearchDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {
    ResponseEntity<MessageResponse> saveSale(SaleReqDto dto);

    PageData search(SaleSearchDto dto);
}