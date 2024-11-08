package com.test.inventory.repository.designation;


import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.designation.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DesignationRepository extends AbstractRepository<Designation> {

    Designation findDesignationByNameIgnoreCase(String name);

    List<Designation> findDesignationByNameIgnoreCaseInAndIsActiveTrue(Set<String> designationNames);

    @Query("SELECT d  from Designation as d where d.isActive = true " +
            "order by d.id DESC")
    List<Designation> findAllAndIsActiveTrue();

    @Query("SELECT d FROM Designation d " +
            "WHERE " +
            "(:query is null or  LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "and d.isActive = :isActive")
    Page<Designation> findByDesignationNameAndIsActive(@Param("query") String query, @Param("isActive")Boolean isActive, Pageable pageable);
}