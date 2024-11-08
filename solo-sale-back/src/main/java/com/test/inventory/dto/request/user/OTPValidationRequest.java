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
public class OTPValidationRequest {
        @NotNull(message = "OTP cannot be null")
        private String otp;

        @Email
        @NotNull(message = "User email cannot be null")
        private String email;
}