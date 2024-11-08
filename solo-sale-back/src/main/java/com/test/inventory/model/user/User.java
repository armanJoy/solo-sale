package com.test.inventory.model.user;

import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "t_users", indexes = {
        @Index(name = "in_user_role_id", columnList = "role_id")
})
public class User extends BaseEntity {

    @NotBlank
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, length = 80)
    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(length = 80)
    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_role_id"))
    private Role role;
}