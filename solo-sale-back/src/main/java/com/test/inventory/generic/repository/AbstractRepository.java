package com.test.inventory.generic.repository;

import com.test.inventory.generic.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@NoRepositoryBean
public interface AbstractRepository<E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

    List<E> findAllByIdInAndIsActive(Set<Long> ids, Boolean isActive);

    Optional<E> findByIdAndIsActiveTrue(Long id);

    List<E> findAllByIdIn(Set<Long> ids);

    List<E> findAllByIdIn(Collection<Long> ids);

    Page<E> findAllByIsActive(Boolean isActive, Pageable pageable);

    List<E> findAllByIsActive(Boolean isActive, Sort sort);
}