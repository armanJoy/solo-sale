package com.test.inventory.dto.request.role;

import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAssignDto implements IDto {

    @Schema(example = "[1,2]")
    private Set<Long> userIds;

    @Schema(example = "ADMIN")
    @NotBlank
    private String name;
}