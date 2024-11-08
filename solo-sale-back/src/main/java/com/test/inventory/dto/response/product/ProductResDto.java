package com.test.inventory.dto.response.product;

import com.test.inventory.generic.payload.request.IDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {

    private Long id;

    private String productName;

    private Long brandId;

    private String brand;

    private Long categoryId;

    private String category;

    private Double mrp;

    private Double sellingPrice;

    private Integer stock;
}