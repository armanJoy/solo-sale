package com.test.inventory.dto.request.role;

import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto implements IDto {
    @Schema(example = "ADMIN")
    @NotBlank(message = "role name is required")
    private String name;
}