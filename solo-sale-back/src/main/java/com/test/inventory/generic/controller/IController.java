package com.test.inventory.generic.controller;

import com.test.inventory.dto.request.SearchDto;
import com.test.inventory.generic.payload.request.IDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IController<D extends IDto, S extends SearchDto> {

//    PageData getAll(Boolean isActive, Pageable pageable);

    <T> T getSingle(@Positive Long id);

    ResponseEntity<MessageResponse> create(@Valid D d);

    ResponseEntity<MessageResponse> update(@Valid D d, @Positive Long id);

    ResponseEntity<MessageResponse> updateActiveStatus(@Positive @PathVariable Long id, @NotNull Boolean isActive);

    ResponseEntity<List<Object>> getAll(@RequestParam(required = false) String orderBy, @RequestParam(required = false) String order);

    ResponseEntity<PageData> search(S searchDto);
}