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
public class ProductCategoryResDto {

    private Long id;

    private String name;
}