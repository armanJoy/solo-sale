package com.test.inventory.dto.request.purchase;

import com.test.inventory.dto.request.sale.SoldProductReqDto;
import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PurchaseReqDto implements IDto {

    @NotEmpty(message = "Product is required")
    @Valid
    private List<PurchasedProductReqDto> purchasedProducts;

    @NotBlank(message = "Vendor is required")
    private String vendor;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;
}