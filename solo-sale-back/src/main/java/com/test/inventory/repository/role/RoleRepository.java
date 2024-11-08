package com.test.inventory.repository.role;

import com.test.inventory.generic.repository.AbstractRepository;
import com.test.inventory.model.role.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {

    Optional<Role> findByNameIgnoreCase(String name);

    Role findByNameIgnoreCaseAndIsActiveTrue(String name);

    List<Role> findAllByIsActiveTrueOrderByNameAsc();
}