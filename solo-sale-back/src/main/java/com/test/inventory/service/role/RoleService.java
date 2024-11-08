package com.test.inventory.service.role;

import com.test.inventory.dto.request.role.RoleAssignDto;
import com.test.inventory.dto.request.role.RoleRequestDto;
import com.test.inventory.dto.response.role.RoleResponse;
import com.test.inventory.dto.response.role.RolewiseUserRespDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.model.role.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    MessageResponse createRole(RoleRequestDto roleRequestDto);

    MessageResponse updateRole(RoleRequestDto roleRequestDto, Long id);

    RoleResponse getById(Long id);

    Role assignRoleToUsers(RoleAssignDto roleAssignDto);

    List<RoleResponse> getAll();

    List<RolewiseUserRespDto> getAssignedUser(Long roleId);

    ResponseEntity<MessageResponse> revokeUserRole(Long userId);

    ResponseEntity<MessageResponse> deleteRole(Long id);

    Role getRole(String name);

    Role getRole(Long id);

}