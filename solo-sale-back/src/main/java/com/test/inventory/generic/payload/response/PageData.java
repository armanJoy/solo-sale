package com.test.inventory.generic.payload.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData {
    List<?> data;
    int totalPages;
    int currentPage;
    long totalElements;
}