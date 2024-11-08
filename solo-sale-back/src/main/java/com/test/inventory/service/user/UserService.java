package com.test.inventory.service.user;

import com.test.inventory.dto.request.employee.EmployeeRequestDto;
import com.test.inventory.dto.request.user.UserRequestDto;
import com.test.inventory.dto.request.user.UserSearchDto;
import com.test.inventory.dto.response.user.UserResponseDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.role.Role;
import com.test.inventory.model.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserResponseDto convertToResponse(User user);

    User convertToEntity(UserRequestDto dto, Role role);

    User findByUserName(String username);

    User findByUserId(Long userId);

    UserResponseDto createUser(UserRequestDto requestDto, Long roleId);
//    User save(UserRequestDto requestDto);

    User createUser(UserRequestDto requestDto);

    Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive);

    PageData search(UserSearchDto userSearchDto, Pageable pageable);

    User update(EmployeeRequestDto employeeRequestDto, Long id);

    Boolean updateActiveStatus(Long id, Boolean isActive);

    List<User> findByUsernameIn(Set<String> userNames);

    List<User> findByEmailIn(Set<String> emails);

    User findById(Long id);
}