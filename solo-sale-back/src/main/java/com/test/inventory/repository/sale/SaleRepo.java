package com.test.inventory.repository.sale;

import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.sale.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SaleRepo extends AbstractRepository<Sale> {

    @Query("""
            SELECT d FROM Sale d
            WHERE
            (:customerName is null or  LOWER(d.customerName) LIKE LOWER(CONCAT('%', :customerName, '%')))
            and (:customerPhone is null or  LOWER(d.customerPhone) LIKE LOWER(CONCAT('%', :customerPhone, '%')))
            and (d.saleDate between :startDate and :endDate)
            and d.isActive = :isActive
            """)
    Page<Sale> findByNameAndIsActive(String customerName, String customerPhone, LocalDate startDate, LocalDate endDate, Boolean isActive, Pageable pageable);

}