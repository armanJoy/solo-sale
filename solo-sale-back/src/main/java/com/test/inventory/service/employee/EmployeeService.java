package com.test.inventory.service.employee;

import com.test.inventory.dto.response.employee.EmployeeResponseDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.model.employee.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();

    Employee findById(Long id);

    Employee findByUserId(Long id);
}