package com.test.inventory.dto.request.user;

import com.test.inventory.dto.request.SearchDto;
import com.test.inventory.generic.payload.request.SDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSearchDto extends SearchDto implements SDto {
    private String username;
    private String fullName;
}