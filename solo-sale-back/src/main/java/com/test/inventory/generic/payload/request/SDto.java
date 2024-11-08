package com.test.inventory.generic.payload.request;

import com.test.inventory.common.constant.ApplicationConstant;

public interface SDto {
    default Boolean getIsActive() {
        return ApplicationConstant.ACTIVE_TRUE;
    }
}