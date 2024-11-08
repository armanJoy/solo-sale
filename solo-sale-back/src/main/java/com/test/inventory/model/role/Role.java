package com.test.inventory.model.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<User> userSet;

    public Role(Long roleId, String name) {
        super();
        this.setId(roleId);
        this.name = name;
    }
}