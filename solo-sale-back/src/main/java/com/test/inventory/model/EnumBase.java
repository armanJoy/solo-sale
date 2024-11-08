package com.test.inventory.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.test.inventory.dto.response.EnumResponseDto;

public interface EnumBase {

    Integer getId();

    String getName();

    @JsonValue
    default EnumResponseDto getEnumResponseDto() {
        return new EnumResponseDto(this.getId(), this.getName());
    }
}