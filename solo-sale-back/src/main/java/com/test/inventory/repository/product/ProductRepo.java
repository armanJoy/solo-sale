package com.test.inventory.repository.product;


import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.designation.Designation;
import com.test.inventory.model.product.Brand;
import com.test.inventory.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends AbstractRepository<Product> {

    Product findByNameIgnoreCase(String name);

    @Query("""
            SELECT d FROM Product d
            WHERE
            (:query is null or  LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')))
            and (:stock is null or d.stock <= :stock)
            and (:categoryId is null or d.category.id = :categoryId)
            and (:brandId is null or d.brand.id = :brandId)
            and d.isActive = :isActive
            """)
    Page<Product> findByNameAndIsActive(String query, Long categoryId, Long brandId, Integer stock, Boolean isActive, Pageable pageable);
}