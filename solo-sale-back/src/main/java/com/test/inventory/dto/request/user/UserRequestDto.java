package com.test.inventory.dto.request.user;

import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements IDto {

    @NotBlank(message = "username is required")
    @Size(max=50,message = "username can not exceed 50 characters")
    @Schema(example = "xyz")
    private String username;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid Email")
    @Size(max=80,message = "email can not exceed 80 characters")
    @Schema(example = "xyz@gmail.com")
    private String email;

    @NotBlank(message = "fullName is required")
    @Size(max=80,message = "fullName can not exceed 80 characters")
    @Schema(example = "Mr. Xyz")
    private String name;

//    @NotEmpty(message = "role is required")
//    private String role;

    @NotBlank(message = "password is required")
    @Size(min = 6,message = "password can not exceed 6 characters")
    @Schema(example = "123456")
    private String password;

    private Boolean isActive = true;
}