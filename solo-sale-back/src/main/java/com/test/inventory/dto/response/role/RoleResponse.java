package com.test.inventory.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResponse {
    private Long id;
    private String roleName;
//    private Set<UserResponseDto> users;
}