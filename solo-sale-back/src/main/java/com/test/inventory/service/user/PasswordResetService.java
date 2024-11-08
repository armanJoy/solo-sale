package com.test.inventory.service.user;

import com.test.inventory.generic.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetService {
    Boolean sendOtpForForgotPassword(String email);

    Boolean validateOTP(String otp, String email);

    Boolean resetForgottenPassword(String email, String otp, String newPassword);

    Boolean changePassword(String email, String oldPassword, String newPassword);

    ResponseEntity<MessageResponse> resetUserPasswordByAdmin(Long userId);
}