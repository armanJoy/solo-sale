package com.test.inventory.dto.response.purchase;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchasedProductResDto {
    private Long id;

    private Long productId;

    private String productName;

    private Double buyingPrice;

    private Integer quantity;

    private Double totalProductPrice;
}