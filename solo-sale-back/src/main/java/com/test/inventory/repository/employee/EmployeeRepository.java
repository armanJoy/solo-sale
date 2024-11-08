package com.test.inventory.repository.employee;

import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends AbstractRepository<Employee> {

    Employee findEmployeeByEmployeeId(String employeeId);

    Optional<Employee> findByIdAndIsActiveTrue(Long id);

    List<Employee> findByEmployeeIdIn(Set<String> employeeIds);

    @Query("SELECT e  from Employee as e where e.isActive = true " +
            "order by e.id DESC")
    List<Employee> findAllAndIsActiveTrue();

    @Query(value = "select e from Employee e " +
            "where (:search is null or lower(e.employeeId) like %:search% or lower(e.name) like %:search%) " +
            "and e.isActive = :isActive")
    Page<Employee> findByEmployeeIdOrName(@Param("search") String search, @Param("isActive") Boolean isActive, Pageable pageable);

    Optional<Employee> findByUserIdAndIsActiveTrue(Long id);
}