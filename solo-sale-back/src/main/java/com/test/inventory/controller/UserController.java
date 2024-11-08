package com.test.inventory.controller;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.common.routes.Router;
import com.test.inventory.dto.request.user.*;
import com.test.inventory.dto.response.user.UserResponseDto;
import com.test.inventory.generic.payload.response.MessageResponse;
import com.test.inventory.generic.payload.response.PageData;
import com.test.inventory.model.role.RoleEnum;
import com.test.inventory.service.user.PasswordResetService;
import com.test.inventory.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = ApiConstants.USER)
public class UserController {

    private final UserService userService;
    private final PasswordResetService passwordResetService;

    /*

    Uncomment these APIs if only in development environment for your test or development purpose

     */

    @PostMapping(ApiConstants.CREATE_USER)
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request, null), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.CREATE_ADMIN_USER)
    public ResponseEntity<UserResponseDto> createAdminUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(userService.createUser(request, RoleEnum.ADMIN.getValue()), HttpStatus.OK);
    }
        /*

    Uncomment these APIs if only in development environment for your test or development purpose

     */

    @PostMapping(path = Router.USER_SEARCH)
    public PageData search(@RequestBody UserSearchDto userSearchDto) {
        return userService.search(userSearchDto, userSearchDto.getPageable());
    }

    @PostMapping(path = Router.FORGOTTEN_PASSWORD)
    public ResponseEntity<MessageResponse> forgotPassword(@Valid @RequestBody OTPRequest otpRequest) {
        Boolean otpGenerated =
                passwordResetService.sendOtpForForgotPassword(otpRequest.getEmail());
        if (otpGenerated) {
            return new ResponseEntity<>(new MessageResponse("OTP sent to your Email address."),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Incorrect email address."),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = Router.VALIDATE_OTP)
    public ResponseEntity<MessageResponse> validateOTP(@Valid @RequestBody OTPValidationRequest otpValidationRequest) {
        Boolean isValidOTP = passwordResetService.validateOTP(otpValidationRequest.getOtp(),
                otpValidationRequest.getEmail());
        if (isValidOTP) {
            return new ResponseEntity<>(new MessageResponse("Valid OTP"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Incorrect OTP"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = Router.RESET_FORGOTTEN_PASSWORD)
    public ResponseEntity<MessageResponse> resetForgottenPassword(@Valid @RequestBody ResetForgottenPasswordRequest passwordRequest) {
        Boolean passwordResetSuccessful =
                passwordResetService.resetForgottenPassword(passwordRequest.getEmail(),
                passwordRequest.getOtp(), passwordRequest.getNewPassword());
        if (passwordResetSuccessful) {
            return new ResponseEntity<>(new MessageResponse("Password Reset Successful."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Request discarded. Try Login or Forget " +
                    "Password."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = Router.CHANGE_PASSWORD)
    public ResponseEntity<MessageResponse> changePassword(@Valid @RequestBody ChangePasswordRequest
                                                                  passwordRequest) {
        Boolean passwordChangeSuccessful =
                passwordResetService.changePassword(passwordRequest.getEmail(),
                passwordRequest.getOldPassword(), passwordRequest.getNewPassword());
        if (passwordChangeSuccessful) {
            return new ResponseEntity<>(new MessageResponse("Password Change Successful."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Incorrect information. Request discarded."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = Router.RESET_PASSWORD)
    public ResponseEntity<MessageResponse> resetUserPasswordByAdmin(@Positive @RequestParam Long targetUserId) {
        return passwordResetService.resetUserPasswordByAdmin(targetUserId);
    }
}