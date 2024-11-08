package com.test.inventory.generic.service;

import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.dto.request.SearchDto;
import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.generic.payload.request.IDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.repository.AbstractRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public abstract class AbstractService<E extends BaseEntity, D extends IDto, S extends SearchDto> implements IService<E, D, S> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
    protected final AbstractRepository<E> repository;

    @Override
    public E create(D d) {
        validateClientData(d, null);
        return saveItem(convertToEntity(d));
    }

    @Override
    public E update(D d, Long id) {
        validateClientData(d, id);
        return saveItem(convertToEntity(d, findById(id)));
    }

    @Override
    public <T> T getSingle(Long id) {
        return convertToResponseDto(findById(id));
    }

    @Override
    public E findById(Long id) {
        if (id != null) {
            return repository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public void updateActiveStatus(Long id, Boolean isActive) {
        E e = findById(id);
        if (e.getIsActive() == isActive) {
            throw CommonServerException.badRequest("Current status is not acceptable");
        }

        e.setIsActive(isActive);
        saveItem(e);
    }

    @Override
    public E saveItem(E entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            LOGGER.error("Save failed for entity {}", entity.getClass().getSimpleName());
            LOGGER.error("Error message: {}", e.getMessage());
            throw CommonServerException.dataSaveException("Couldn't save. Try again later");
        }
    }

    @Override
    public List<E> saveItemList(List<E> entityList) {
        try {
            if (CollectionUtils.isEmpty(entityList)) {
                return entityList;
            }
            return repository.saveAll(entityList);
        } catch (Exception e) {
            String entityName = entityList.get(0).getClass().getSimpleName();
            LOGGER.error("Save failed for entity {}", entityName);
            LOGGER.error("Error message: {}", e.getMessage());
            throw e;
        }
    }


    @Override
    public List<Object> getAll(String orderBy, String order) {
        List<E> pagedData = repository.findAllByIsActive(Boolean.TRUE,
                Sort.by(Sort.Direction.fromString(order), orderBy));
        List<Object> models = pagedData.stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return models;
    }

    @Override
    public List<E> findAllByIds(Collection<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    protected abstract <T> T convertToResponseDto(E e);

    protected abstract E convertToEntity(D d);

    protected abstract E convertToEntity(D d, E entity);

    @Override
    public PageData search(S searchDto) {
        Page<E> pagedData = repository.findAllByIsActive(searchDto.isActive(), searchDto.getPageable());
        List<Object> models = pagedData.getContent().stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return PageData.builder()
                .data(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }
}