package com.test.inventory.dto.request.product;

import com.test.inventory.generic.payload.request.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqDto implements IDto {

    @NotBlank(message = "productName is required")
    private String productName;

    @Positive(message = "brand is required")
    private Long brandId;

    @Positive(message = "category is required")
    private Long categoryId;

    @Positive(message = "mrp is required")
    private Double mrp;

    @Positive(message = "sellingPrice is required")
    private Double sellingPrice;
}