package com.test.inventory.dto.request.product;

import com.test.inventory.dto.request.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto extends SearchDto {

    private String name;

    private Integer stockNumber;

    private Long categoryId;

    private Long brandId;
}