package com.test.inventory.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(description = "give your username", example = "armanjoy")
    @NotBlank(message = "username required")
    private String username;

    @Schema(description = "give password", example = "123456")
    @NotBlank(message = "password required")
    private String password;
}