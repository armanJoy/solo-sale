package com.test.inventory.service.employee;

import com.test.inventory.common.Utils.Helper;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.dto.request.employee.EmployeeRequestDto;
import com.test.inventory.dto.request.employee.EmployeeSearchDto;
import com.test.inventory.dto.request.user.UserRequestDto;
import com.test.inventory.dto.response.employee.EmployeeResponseDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.generic.service.AbstractService;
import com.test.inventory.model.employee.Employee;
import com.test.inventory.model.user.User;
import com.test.inventory.repository.employee.EmployeeRepository;
import com.test.inventory.service.designation.DesignationService;
import com.test.inventory.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends AbstractService<Employee, EmployeeRequestDto, EmployeeSearchDto> implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final DesignationService designationService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserService userService, DesignationService designationService) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.designationService = designationService;
    }

    @Override
    @Transactional
    public Employee create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = convertToEntity(employeeRequestDto);
        UserRequestDto userRequestDto = employeeRequestDto.getUserDto();
        userRequestDto.setName(employeeRequestDto.getName());
        userRequestDto.setUsername(employeeRequestDto.getEmployeeId().trim());
        employee.setUser(userService.createUser(userRequestDto));
        saveItem(employee);
        return employee;
    }

    @Override
    @Transactional
    public Employee update(EmployeeRequestDto employeeRequestDto, Long id) {
        Employee exEmployee = findById(id);
        User user = userService.update(employeeRequestDto, exEmployee.getUserId());
        exEmployee = saveItem(convertToEntity(employeeRequestDto, exEmployee));
        exEmployee.setUser(user);
        return exEmployee;
    }

    @Override
    @Transactional
    public void updateActiveStatus(Long id, Boolean isActive) {
        Employee employee = findById(id);
        if (employee.getIsActive() == isActive) {
            throw CommonServerException.badRequest("Nothing to change");
        }

        employee.setIsActive(isActive);
        saveItem(employee);

        userService.updateActiveStatus(employee.getUser().getId(), isActive);
    }

    @Override
    protected EmployeeResponseDto convertToResponseDto(Employee employee) {
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        responseDto.setId(employee.getId());
        responseDto.setName(employee.getName());
        responseDto.setEmployeeId(employee.getEmployeeId());
        responseDto.setPhoneNumber(employee.getPhoneNumber());
        responseDto.setJoiningDate(employee.getJoiningDate());
        responseDto.setJobDuration(Helper.dateDifference(employee.getJoiningDate()));
        if (Objects.nonNull(employee.getUser())) {
            responseDto.setUserDto(userService.convertToResponse(employee.getUser()));
        }
        if (Objects.nonNull(employee.getDesignation())) {
            responseDto.setDesignation(employee.getDesignation().getName());
            responseDto.setDesignationId(employee.getDesignation().getId());
        }
        return responseDto;
    }

    @Override
    protected Employee convertToEntity(EmployeeRequestDto employeeRequestDto) {
        return mapToEntity(new Employee(), employeeRequestDto);
    }

    //For update
    @Override
    protected Employee convertToEntity(EmployeeRequestDto employeeRequestDto, Employee entity) {
        return mapToEntity(entity, employeeRequestDto);
    }

    private Employee mapToEntity(Employee entity, EmployeeRequestDto dto) {
        entity.setName(dto.getName());
        entity.setJoiningDate(dto.getJoiningDate());
        setEmployeeIdWithValidation(entity, dto.getEmployeeId().trim());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDesignation(designationService.findSingle(dto.getDesignationId()));
        return entity;

    }

    private void setEmployeeIdWithValidation(Employee entity, String employeeId) {
        Employee emp = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if(Objects.isNull(emp) || emp.getId().equals(entity.getId())){
            entity.setEmployeeId(employeeId);
        } else if (emp.getIsActive()) {
            throw CommonServerException.badRequest("Duplicate employee ID in active list");
        } else {
            throw CommonServerException.badRequest("Duplicate employee ID in archive list");
        }
    }

    @Override
    public Employee findById(Long id) {
        if(Objects.nonNull(id)){
            return repository.findById(id).orElse(null);
        }else return null;

    }

    @Override
    public Employee findByUserId(Long id) {
        return employeeRepository.findByUserIdAndIsActiveTrue(id)
                .orElse(null);
    }

    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeRepository.findAllAndIsActiveTrue().stream().map(this::convertToResponseDto).collect(Collectors.toList());

    }

    @Override
    public PageData search(EmployeeSearchDto searchDto) {
        Page<Employee> employees = employeeRepository.findByEmployeeIdOrName(Objects.nonNull(searchDto.getSearch()) ?
                searchDto.getSearch().toLowerCase() : null, searchDto.isActive(), searchDto.getPageable());

        return PageData.builder()
                .data(employees.getContent().stream().map(this::convertToResponseDto).collect(Collectors.toList()))
                .totalPages(employees.getTotalPages())
                .totalElements(employees.getTotalElements())
                .currentPage(searchDto.getPageable().getPageNumber() + 1)
                .build();
    }

    public List<Employee> findByEmployeeIdIn(Set<String> employeeIds) {
        return employeeRepository.findByEmployeeIdIn(employeeIds);
    }
}