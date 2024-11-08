package com.test.inventory.dto.request.purchase;

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
public class PurchaseSearchDto extends SearchDto {

    private String product;

    private String vendor;

    private LocalDate start;

    private LocalDate end;
}