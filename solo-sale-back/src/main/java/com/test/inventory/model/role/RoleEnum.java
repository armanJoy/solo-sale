package com.test.inventory.model.role;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN(1L),
    OFFICER(2L),
    MAINTAINER(3L),
    INV_SYS_ADMIN(88L),
    ;

    private final Long value;

    RoleEnum(Long value) {
        this.value = value;
    }

}