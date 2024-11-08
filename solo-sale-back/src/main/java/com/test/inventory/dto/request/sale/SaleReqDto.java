package com.test.inventory.dto.request.sale;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.config.validator.NumberRange;
import com.test.inventory.generic.payload.request.IDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
public class SaleReqDto implements IDto {

    @NotEmpty(message = "Product is required")
    @Valid
    private List<SoldProductReqDto> soldProducts;

    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Pattern(regexp = ApplicationConstant.PHONE_NUMBER_PATTERN, message = "Invalid phone number")
    private String customerPhone;

    @NotBlank(message = "Customer address is required")
    private String customerAddress;

    @NumberRange(max = 70, message = "Discount can be 0 to 70 percent")
    private Double discountPercentage = 0.0;

    @PositiveOrZero(message = "deliveryCharge is required")
    private Double deliveryCharge = 0.0;

}