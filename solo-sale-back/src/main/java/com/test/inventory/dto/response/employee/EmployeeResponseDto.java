package com.test.inventory.dto.response.employee;

import com.test.inventory.dto.response.user.UserResponseDto;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String employeeId;
    private String phoneNumber;
    private LocalDate joiningDate;
    private String jobDuration;
    private UserResponseDto userDto;
    private String designation;
    private Long designationId;

}