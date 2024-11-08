package com.test.inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {

    private String message;

    private boolean isError = false;

    private Object data;

}