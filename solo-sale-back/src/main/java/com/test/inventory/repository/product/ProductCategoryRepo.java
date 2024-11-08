package com.test.inventory.repository.product;


import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.product.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepo extends AbstractRepository<ProductCategory> {

    ProductCategory findByNameIgnoreCase(String name);

    @Query("SELECT d FROM ProductCategory d " +
            "WHERE " +
            "(:query is null or  LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "and d.isActive = :isActive")
    Page<ProductCategory> findByNameAndIsActive(@Param("query") String query, @Param("isActive")Boolean isActive, Pageable pageable);
}