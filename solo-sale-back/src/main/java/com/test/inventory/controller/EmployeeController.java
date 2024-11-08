package com.test.inventory.controller;

import com.test.inventory.common.Utils.Helper;
import com.test.inventory.common.constant.ApiMessage;
import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.employee.EmployeeRequestDto;
import com.test.inventory.dto.request.employee.EmployeeSearchDto;
import com.test.inventory.dto.request.employee.EmployeeUpdateDto;
import com.test.inventory.dto.response.employee.EmployeeResponseDto;
import com.test.inventory.generic.controller.AbstractController;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.service.IService;
import com.test.inventory.model.employee.Employee;
import com.test.inventory.service.employee.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Validated
@RestController
@RequestMapping(path = Router.EMPLOYEE)
@Tag(name = ApiConstants.EMPLOYEE)
public class EmployeeController extends AbstractController<Employee, EmployeeRequestDto, EmployeeSearchDto> {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(IService<Employee, EmployeeRequestDto, EmployeeSearchDto> service, EmployeeServiceImpl employeeService) {
        super(service);
        this.employeeService = employeeService;
    }

    @PutMapping(Router.EMPLOYEE_UPDATE)
    public ResponseEntity<MessageResponse> update(@RequestBody EmployeeUpdateDto dto, @PathVariable Long id) {
        Employee entity = service.update(dto, id);
        return ResponseEntity.ok(new MessageResponse(Employee.class.getSimpleName(),
                ApiMessage.UPDATED_SUCCESSFULLY, entity.getId()));
    }


    @GetMapping(value = Router.EMPLOYEE_LIST)
    @Operation(summary = "API Endpoints for list of all employees")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}