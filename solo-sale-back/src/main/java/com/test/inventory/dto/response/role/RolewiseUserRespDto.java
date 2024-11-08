package com.test.inventory.dto.response.role;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class RolewiseUserRespDto implements Serializable {

    private int sl;
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Long roleId;
    private String roleName;
}