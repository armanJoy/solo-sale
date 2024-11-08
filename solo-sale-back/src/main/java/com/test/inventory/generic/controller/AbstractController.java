package com.test.inventory.generic.controller;

import com.test.inventory.common.constant.ApiMessage;
import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.dto.request.SearchDto;
import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.generic.payload.request.IDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.IService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Validated
public abstract class AbstractController<E extends BaseEntity, D extends IDto, S extends SearchDto> implements IController<D, S> {

    protected final IService<E, D, S> service;


    private String getModelName(E entity) {
        return entity.getClass().getSimpleName();
    }


    @Override
    @GetMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public <T> T getSingle(@PathVariable Long id) {
        return service.getSingle(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@RequestBody D dto) {
        E entity = service.create(dto);
        return ResponseEntity.ok(new MessageResponse(getModelName(entity), ApiMessage.CREATED_SUCCESSFULLY, entity.getId()));
    }

    @Override
    @PutMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public ResponseEntity<MessageResponse> update(@RequestBody D dto, @PathVariable Long id) {
        E entity = service.update(dto, id);
        return ResponseEntity.ok(new MessageResponse(getModelName(entity), ApiMessage.UPDATED_SUCCESSFULLY, entity.getId()));
    }

    @Override
    @PatchMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public ResponseEntity<MessageResponse> updateActiveStatus(@PathVariable Long id, @RequestParam(name = "isActive") Boolean isActive) {
        service.updateActiveStatus(id, isActive);
        return ResponseEntity.ok(new MessageResponse(ApiMessage.ACTIVE_STATUS_CHANGED_SUCCESSFULLY));
    }

    @Override
    @GetMapping(ApiConstants.GET_LIST)
    public ResponseEntity<List<Object>> getAll(@Schema(example = "id") @RequestParam(required = false) String orderBy,
                                               @Schema(example = "DESC") @RequestParam(required = false) String order) {
        return ResponseEntity.ok(service.getAll(Objects.nonNull(orderBy) ? orderBy : "id",
                Objects.nonNull(order) ? order : "DESC"));
    }

    @Override
    @PostMapping(ApiConstants.SEARCH)
    public ResponseEntity<PageData> search(@RequestBody S searchDto) {
        return new ResponseEntity(service.search(searchDto), HttpStatus.OK);
    }
}