package com.test.inventory.repository.purchase;


import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.product.Product;
import com.test.inventory.model.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PurchaseRepo extends AbstractRepository<Purchase> {

    @Query("""
            SELECT d FROM Purchase d
            WHERE
            (:vendor is null or  LOWER(d.vendor) LIKE LOWER(CONCAT('%', :vendor, '%')))
            and (d.purchaseDate between :startDate and :endDate)
            and d.isActive = :isActive
            """)
    Page<Purchase> findByNameAndIsActive(String vendor, LocalDate startDate, LocalDate endDate, Boolean isActive, Pageable pageable);

}