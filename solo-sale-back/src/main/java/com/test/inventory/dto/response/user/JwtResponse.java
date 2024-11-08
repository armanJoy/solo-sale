package com.test.inventory.dto.response.user;

import com.test.inventory.common.constant.ApplicationConstant;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String token;

    private String refreshToken;

    @Builder.Default
    private String type = ApplicationConstant.AUTHORIZATION_TYPE_BEARER;

    private String roleName;

    private Long roleId;

    private String fullName;

    private Long userId;

    private String userName;

    private Long employeeId;
}