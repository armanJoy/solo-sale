package com.test.inventory.dto.request.product;

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
public class ProductCategoryReqDto implements IDto {

    @NotBlank(message = "name is required")
    private String name;
}