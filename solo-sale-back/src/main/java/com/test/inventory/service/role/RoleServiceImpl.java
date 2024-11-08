package com.test.inventory.service.role;

import com.test.inventory.common.Utils.Helper;
import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.dto.request.role.RoleAssignDto;
import com.test.inventory.dto.request.role.RoleRequestDto;
import com.test.inventory.dto.response.role.RoleResponse;
import com.test.inventory.dto.response.role.RolewiseUserRespDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.model.role.Role;
import com.test.inventory.model.role.RoleEnum;
import com.test.inventory.model.user.User;
import com.test.inventory.repository.role.RoleRepository;
import com.test.inventory.repository.user.UserRepository;
import com.test.inventory.service.user.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserService userService, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> pagedData =
                roleRepository.findAllByIsActiveTrueOrderByNameAsc().stream().filter(item->!item.getName().equalsIgnoreCase(RoleEnum.INV_SYS_ADMIN.name())).toList();
        return pagedData.stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void populateRole() {
        List<Role> allRoles = roleRepository.findAll();

        RoleEnum[] roleEnums = RoleEnum.values();

        for (RoleEnum roleEnum : roleEnums) {
            if (allRoles.stream().filter(ncEntity -> ncEntity.getName().equalsIgnoreCase(roleEnum.name())).findAny().isEmpty()) {
                Role newRole = Role.builder().name(roleEnum.name()).userSet(new HashSet<>()).build();
                newRole.setId(roleEnum.getValue());
                allRoles.add(newRole);
            }
        }

        roleRepository.saveAll(allRoles);
    }

    protected RoleResponse convertToResponseDto(Role role) {
        RoleResponse response = new RoleResponse();
        response.setRoleName(role.getName());
        response.setId(role.getId());
        return response;
    }

    protected Role convertToEntity(RoleRequestDto roleRequestDto) {
        return convertToEntity(roleRequestDto, new Role());
    }

    protected Role convertToEntity(RoleRequestDto roleRequestDto, Role role) {
        if (Objects.nonNull(roleRequestDto.getName())) {
            setNameWithValidation(roleRequestDto.getName(), role);
        }
        return role;
    }

    private void setNameWithValidation(String name, Role newRole) {
        Role savedRole = roleRepository.findByNameIgnoreCaseAndIsActiveTrue(name);
        if (Objects.nonNull(savedRole) && !(savedRole.getId().equals(newRole.getId()))) {
            throw CommonServerException.badRequest("Role already exist");
        } else {
            newRole.setName(name);
        }
    }

    @Override
    public MessageResponse createRole(RoleRequestDto roleRequestDto) {
        Role role = convertToEntity(roleRequestDto);
        Role savedRole = roleRepository.save(role);
        return new MessageResponse("Role", "Role created", role.getId());
    }

    @Override
    public MessageResponse updateRole(RoleRequestDto roleRequestDto, Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (Objects.nonNull(role)) {
            role = convertToEntity(roleRequestDto, role);
            role = roleRepository.save(role);
        } else {
            throw new RuntimeException("Role not found with Id: " + roleId);
        }
        return new MessageResponse("Role", "Role updated", role.getId());
    }

    @Override
    public RoleResponse getById(@PathVariable Long id) {
        Role savedRole = roleRepository.findById(id).orElse(null);
        if (savedRole != null) {
            return convertToResponseDto(savedRole);
        } else {
            throw CommonServerException.notFound("Role not found");
        }
    }

    @Override
    public Role assignRoleToUsers(RoleAssignDto roleAssignDto) {
        Optional<Role> savedRole = roleRepository.findByNameIgnoreCase(roleAssignDto.getName());
        if (savedRole.isPresent() && !savedRole.get().getName().equalsIgnoreCase(RoleEnum.INV_SYS_ADMIN.name())) {
            validateClientData(roleAssignDto, savedRole.get());
            Role role = convertToEntity(roleAssignDto, savedRole.get());
            return roleRepository.save(role);
        } else {
            throw new CommonServerException("Invalid role", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateClientData(RoleAssignDto dto, Role entity) {
        if (Objects.nonNull(dto.getName()) && !dto.getName().trim().isBlank()) {
            Optional<Role> optionalRole = roleRepository.findByNameIgnoreCase(dto.getName().trim());

            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                if (Objects.nonNull(entity) && role.equals(entity)) {
                    return;
                }
            }

        }
    }


    protected Role convertToEntity(RoleAssignDto roleAssignDto, Role entity) {
        return mapToEntity(roleAssignDto, entity);
    }

    private Role mapToEntity(RoleAssignDto dto, Role entity) {
        Set<Long> newUserIds = Objects.nonNull(dto.getUserIds()) ? dto.getUserIds() : new HashSet<>();
//        Set<User> newUsers = userRepository.findAllByIdInAndIsActive(newUserIds, true);
        Set<User> existingUser = entity.getUserSet();
        Set<Long> existingUserSet = Objects.nonNull(existingUser) ?
                existingUser.stream().map(User::getId).collect(Collectors.toSet()) : new HashSet<>();
        newUserIds.removeIf(user -> existingUserSet.contains(user));

        return setUserToRole(entity, newUserIds, new HashSet<>());
    }

    public Role setUserToRole(Role entity, Set<Long> userIds, Set<User> userSet) {
        Set<User> newUserSet = userService.findAllUserByIdIn(userIds, ApplicationConstant.ACTIVE_TRUE);
        if (Objects.nonNull(entity.getId())) {
            if (userIds.contains(ApplicationConstant.ONE) && !entity.getId().equals(ApplicationConstant.ONE)) {
                throw CommonServerException.badRequest("Cannot change Admin");
            }
            newUserSet.forEach(user -> user.setRole(entity));
            userSet.addAll(newUserSet);
            entity.setUserSet(userSet);
            return entity;
        }
        if (!newUserSet.isEmpty()) {
            entity.setUserSet(newUserSet);
        }
        return entity;
    }

    @Override
    public Role getRole(String name) {
        Optional<Role> savedRole = roleRepository.findByNameIgnoreCase(name);
        return savedRole.orElse(null);
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> savedRole = roleRepository.findById(id);
        return savedRole.orElse(null);
    }

    @Override
    public List<RolewiseUserRespDto> getAssignedUser(Long roleId) {
        Optional<Role> role = roleRepository.findByIdAndIsActiveTrue(roleId);
        AtomicInteger count = new AtomicInteger(0);
        return role.map(value -> value.getUserSet().stream().sorted(Comparator.comparing(User::getFullName
        )).map(item -> RolewiseUserRespDto.builder()
                .sl(count.incrementAndGet())
                .id(item.getId())
                .username(item.getUsername())
                .fullName(item.getFullName())
                .email(item.getEmail())
                .roleId(value.getId())
                .roleName(value.getName())
                .build()).toList()).orElseGet(ArrayList::new);

    }

    @Override
    public ResponseEntity<MessageResponse> revokeUserRole(Long userId) {
        if (!Helper.getCurrentUser().getId().equals(userId)) {
            Optional<User> userOp = userRepository.findById(userId);

            if (userOp.isPresent()) {
                User user = userOp.get();
                user.setRole(null);
                userRepository.save(user);
                return new ResponseEntity<>(new MessageResponse("User role revoked"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(new MessageResponse("You are not allowed to revoked this user's role"), HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional
    public ResponseEntity<MessageResponse> deleteRole(Long id) {
        Optional<Role> roleOp = roleRepository.findByIdAndIsActiveTrue(id);
        if (roleOp.isPresent()) {
            Role role = roleOp.get();
            if (Objects.isNull(role.getUserSet()) || role.getUserSet().isEmpty()) {
                roleRepository.delete(role);
                return new ResponseEntity<>(new MessageResponse("Role deleted"), HttpStatus.OK);
            }

            return new ResponseEntity<>(new MessageResponse("Cannot delete role. First you have to revoke " +
                    "all users from this role."), HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>(new MessageResponse("Role not found"), HttpStatus.NOT_FOUND);
        }

    }
}