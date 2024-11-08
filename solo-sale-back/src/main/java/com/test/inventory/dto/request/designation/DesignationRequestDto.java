package com.test.inventory.dto.request.designation;

import com.test.inventory.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class DesignationRequestDto implements IDto {

    @NotBlank(message = "The employee designation name is required")
    @Schema(example = "Officer")
    @Size(max = 50, message = "The employee designation name must not exceed 50 characters.")
    private String name;
}