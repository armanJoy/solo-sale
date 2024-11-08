package com.test.inventory.dto.response.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResDto {

    private Long id;

    private String vendor;

    private LocalDate purchaseDate;

    private List<PurchasedProductResDto> purchasedProducts;

    private Double totalPrice;
}