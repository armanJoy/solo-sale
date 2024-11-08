package com.test.inventory.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetForgottenPasswordRequest {
        @Email
        @NotNull
        private String email;

        @NotNull
        private String otp;

        @NotNull
        private String newPassword;
}