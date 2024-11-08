package com.test.inventory.dto.request.designation;

import com.test.inventory.dto.request.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DesignationSearchDto extends SearchDto {

    private String search;
}