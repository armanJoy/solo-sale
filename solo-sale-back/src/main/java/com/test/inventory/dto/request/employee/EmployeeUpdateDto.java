package com.test.inventory.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.inventory.dto.request.user.UserRequestDto;

public class EmployeeUpdateDto extends EmployeeRequestDto {

    @JsonIgnore
    private final UserRequestDto userDto = new UserRequestDto();
}