package com.test.inventory.dto.response.sale;

import com.test.inventory.config.validator.NumberRange;
import com.test.inventory.generic.payload.request.IDto;
import jakarta.validation.constraints.NotBlank;
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
public class SaleResDto {

    private Long id;

    private List<SoldProductResDto> soldProducts;

    private LocalDate saleDate;

    private String customerName;

    private String customerPhone;

    private String customerAddress;

    private Double discountPercentage = 0.0;

    private Double deliveryCharge = 0.0;

    private Double totalProductPrice;

    private Double totalPrice;

    private boolean paid;

    private boolean delivered;
}