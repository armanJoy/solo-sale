package com.test.inventory.dto.request.purchase;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
public class PurchasedProductReqDto {

    @Positive(message = "Product is required")
    private Long productId;

    @Positive(message = "Quantity is required")
    private Integer quantity;

    @Positive(message = "Price is required")
    private Double price;
}