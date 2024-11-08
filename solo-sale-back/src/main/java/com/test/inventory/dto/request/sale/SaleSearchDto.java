package com.test.inventory.dto.request.sale;

import com.test.inventory.dto.request.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleSearchDto extends SearchDto {

    private String customerName;
    
    private String customerPhone;

    private LocalDate start;

    private LocalDate end;
}