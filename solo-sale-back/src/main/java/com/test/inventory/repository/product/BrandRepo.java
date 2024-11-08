package com.test.inventory.repository.product;


import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.product.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends AbstractRepository<Brand> {

    Brand findByNameIgnoreCase(String name);

    @Query("SELECT d FROM Brand d " +
            "WHERE " +
            "(:query is null or  LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "and d.isActive = :isActive")
    Page<Brand> findByNameAndIsActive(@Param("query") String query, @Param("isActive")Boolean isActive, Pageable pageable);
}