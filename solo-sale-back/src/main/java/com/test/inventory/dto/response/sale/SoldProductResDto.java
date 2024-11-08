package com.test.inventory.dto.response.sale;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SoldProductResDto {
    private Long id;

    private Long productId;

    private String productName;

    private Double sellingPrice;

    private Integer quantity;

    private Double totalPrice;
}