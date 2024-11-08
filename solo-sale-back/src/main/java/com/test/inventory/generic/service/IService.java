package com.test.inventory.generic.service;

import com.test.inventory.dto.request.SearchDto;
import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.generic.payload.request.IDto;
import com.test.inventory.generic.payload.response.PageData;

import java.util.Collection;
import java.util.List;

public interface IService<E extends BaseEntity, D extends IDto, S extends SearchDto> {

    E create(D d);

    E update(D d, Long id);

    <T> T getSingle(Long id);

    E findById(Long id);

    void updateActiveStatus(Long id, Boolean b);

    E saveItem(E entity);

    List<E> saveItemList(List<E> entityList);

    default void validateClientData(D d, Long id) {
    }

    List<Object> getAll(String orderBy, String order);

    List<E> findAllByIds(Collection<Long> ids);

    PageData search(S searchDto);
}