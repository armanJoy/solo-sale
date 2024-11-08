package com.test.inventory.dto.request.sale;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
public class SoldProductReqDto {

    @Positive(message = "Product is required")
    private Long productId;

    @Positive(message = "Quantity is required")
    private Integer quantity;
}