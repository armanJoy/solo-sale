package com.test.inventory.dto.request.product;

import com.test.inventory.dto.request.SearchDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandSearchDto extends SearchDto {

    private String name;
}