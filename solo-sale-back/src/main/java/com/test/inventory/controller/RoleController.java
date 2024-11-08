package com.test.inventory.controller;


import com.test.inventory.common.constant.ApiMessage;
import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.role.RoleAssignDto;
import com.test.inventory.dto.response.role.RoleResponse;
import com.test.inventory.dto.response.role.RolewiseUserRespDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.model.role.Role;
import com.test.inventory.service.role.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = Router.ROLE)
@Tag(name = ApiConstants.ROLE)
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

//    @PostMapping()
//    public ResponseEntity<MessageResponse> create(@Valid @RequestBody RoleRequestDto dto) {
//        MessageResponse res = roleService.createRole(dto);
//        return ResponseEntity.ok(res);
//    }

//    @GetMapping(value = "/{id}")
//    public RoleResponse getById(@Positive @PathVariable Long id) {
//        return roleService.getById(id);
//    }

//    @PutMapping()
//    public ResponseEntity<MessageResponse> update(@Valid @RequestBody RoleRequestDto dto, @RequestParam Long roleId) {
//        MessageResponse res = roleService.updateRole(dto, roleId);
//        return ResponseEntity.ok(res);
//    }

    @PutMapping(value = Router.ASSIGN_ROLE)
    public ResponseEntity<MessageResponse> assignRoleToUsers(@Valid @RequestBody RoleAssignDto dto) {
        Role entity = roleService.assignRoleToUsers(dto);
        return ResponseEntity.ok(new MessageResponse(Role.class.getSimpleName(),
                ApiMessage.CREATED_SUCCESSFULLY, entity.getId()));
    }

    @GetMapping(value = ApiConstants.GET_LIST)
    public List<RoleResponse> getAll() {
        return roleService.getAll();
    }

    @GetMapping(value = Router.ASSIGNED_USERS)
    public List<RolewiseUserRespDto> getAssignedUser(@Positive @RequestParam Long roleId) {
        return roleService.getAssignedUser(roleId);
    }

    @DeleteMapping(value = Router.REVOKE_USER_ROLE)
    public ResponseEntity<MessageResponse> revokeUserRole(@Positive @PathVariable Long id) {
        return roleService.revokeUserRole(id);
    }

//    @DeleteMapping(value = ApiConstants.PATH_VARIABLE_BY_ID)
//    public ResponseEntity<MessageResponse> deleteRole(@PathVariable @Positive Long id) {
//        return roleService.deleteRole(id);
//    }
}