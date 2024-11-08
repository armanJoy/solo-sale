package com.test.inventory.service.user;

import com.test.inventory.common.Utils.Helper;
import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.config.authentication.service.CustomUserDetails;
import com.test.inventory.dto.request.employee.EmployeeRequestDto;
import com.test.inventory.dto.request.user.UserRequestDto;
import com.test.inventory.dto.request.user.UserSearchDto;
import com.test.inventory.dto.response.user.UserResponseDto;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.role.Role;
import com.test.inventory.model.user.User;
import com.test.inventory.repository.role.RoleRepository;
import com.test.inventory.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDto convertToResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .roleId(Objects.nonNull(user.getRole()) ? user.getRole().getId() : null)
                .roleName(Objects.nonNull(user.getRole()) ? user.getRole().getName() : null)
                .email(user.getEmail())
                .createdAt(user.getCreatedTime())
                .updatedAt(user.getUpdatedTime())
                .createdById((Objects.nonNull(user.getCreatedBy())) ? user.getCreatedBy().getId() : null)
                .updatedById(Objects.nonNull(user.getUpdatedBy()) ? user.getUpdatedBy().getId() : null)
                .build();
    }

    @Override
    public User convertToEntity(UserRequestDto dto, Role role) {
        User entity = new User();
        entity.setUsername(dto.getUsername().trim().toLowerCase());
        entity.setEmail(dto.getEmail().trim().toLowerCase());
        entity.setFullName(dto.getName());
        entity.setRole(role);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return entity;
    }

    @Override
    public User findByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> CommonServerException.notFound("User not found"));
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> CommonServerException.notFound("User not " +
                "found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return CustomUserDetails.build(user.get());
        } else {
            throw new UsernameNotFoundException("User Not Found" + username);
        }
    }


    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto, Long roleId) {
        Role role = roleValidation(roleId);
        User user = convertToEntity(requestDto, role);
        validateClientData(requestDto, user);
        return convertToResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public User createUser(UserRequestDto requestDto) {
        if (Objects.isNull(requestDto.getPassword()) || requestDto.getPassword().trim().isBlank()) {
            throw CommonServerException.badRequest("Invalid password");
        }
        User user = convertToEntity(requestDto, null);
        validateClientData(requestDto, user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(EmployeeRequestDto employeeRequestDto, Long id) {
        User exUser = userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> CommonServerException.notFound("User not found"));
        validateClientData(employeeRequestDto.getUserDto(), exUser);
        exUser.setUsername(employeeRequestDto.getEmployeeId().trim());
        exUser.setFullName(employeeRequestDto.getName());
        return userRepository.save(exUser);
    }

    @Override
    public Set<User> findAllUserByIdIn(Set<Long> userIds, Boolean isActive) {
        return userRepository.findAllByIdInAndIsActive(userIds, isActive);
    }

    private Role roleValidation(Long roleId) {
        Role roleOb = null;
        if (Objects.isNull(roleId)) {
            return roleOb;
        }
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            roleOb = role.get();
        } else {
            throw CommonServerException.notFound("Role not found");
        }
        return roleOb;
    }

    @Override
    public PageData search(UserSearchDto userSearchDto, Pageable pageable) {


        Page<User> pageData = userRepository.findAllUserByUserName(Objects.isNull(userSearchDto.getUsername()) ? null :
                        userSearchDto.getUsername().toLowerCase(),
                userSearchDto.getIsActive(), pageable);
        List<UserResponseDto> models = pageData.getContent().stream().map(this::convertToResponse)
                .collect(Collectors.toList());

        return PageData.builder()
                .data(models)
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }


    @Override
    public Boolean updateActiveStatus(Long id, Boolean isActive) {
        User user = findByUserId(id);
        if (user.getUsername().equalsIgnoreCase("admin") || user.getUsername().equals(ApplicationConstant.ADMIN_USER_NAME)) {
            throw CommonServerException.badRequest("Cannot remove admin user");
        }
        if (user.getIsActive() == isActive) {
            throw CommonServerException.badRequest("Nothing to change");
        }

        User currentUser = Helper.getCurrentUser();
        if (Objects.nonNull(currentUser) && Objects.equals(currentUser.getId(), id)) {
            user.setIsActive(isActive);
            userRepository.save(user);
            return true;
        }

        user.setIsActive(isActive);
        userRepository.save(user);
        return false;
    }

    @Override
    public List<User> findByUsernameIn(Set<String> userNames) {
        return userRepository.findByUsernameIgnoreCaseIn(userNames);
    }

    @Override
    public List<User> findByEmailIn(Set<String> emails) {
        return userRepository.findByEmailIgnoreCaseIn(emails);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void validateClientData(UserRequestDto requestDto, User entity) {
        User user = userRepository.findByUsernameOrEmail
                (requestDto.getUsername(), requestDto.getEmail());

            if(Objects.isNull(user) || user.getId().equals(entity.getId())){
                return;
            }
            if (user.getUsername().equalsIgnoreCase(requestDto.getUsername())) {
                if(user.getIsActive()){
                    throw new RuntimeException(ApplicationConstant.USER_NAME_EXIST_MSG);
                } else {
                    throw new RuntimeException(ApplicationConstant.USER_NAME_EXIST_MSG_ARCHIVE);
                }
            }
            if (user.getEmail().equalsIgnoreCase(requestDto.getEmail())) {
                if(user.getIsActive()){
                    throw new RuntimeException(ApplicationConstant.USER_EMAIL_ALREADY_EXIST);
                } {
                    throw new RuntimeException(ApplicationConstant.USER_EMAIL_ALREADY_EXIST_ARCHIVE);
                }
            }

    }
}