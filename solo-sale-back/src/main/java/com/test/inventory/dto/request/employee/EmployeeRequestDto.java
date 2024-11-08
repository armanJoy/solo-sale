package com.test.inventory.dto.request.employee;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.dto.request.user.UserRequestDto;
import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto implements IDto {

    @NotBlank(message = "Employee name is required")
    @Schema(example = "Mr. Xyz")
    @Size(max = 80, message = "Name of the employee must not exceed 80 characters.")
    private String name;

    @NotBlank(message = "employee id is required")
    @Schema(example = "EG-000100")
    @Size(max = 50, message = "Employee id must not exceed 50 characters.")
    private String employeeId;

    @Pattern(regexp = ApplicationConstant.PHONE_NUMBER_PATTERN, message = "Invalid phone number")
    @Schema(example = "01738014685")
    private String phoneNumber;

    @NotNull(message = "Joining date required")
    private LocalDate joiningDate;

    private UserRequestDto userDto = new UserRequestDto();

    @Positive(message = "Employee designation not found")
    @Schema(example = "1")
    private Long designationId;
}