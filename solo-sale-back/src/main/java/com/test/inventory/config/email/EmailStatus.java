package com.test.inventory.config.email;

import com.test.inventory.common.constant.ApplicationConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class EmailStatus {
    private final String to;
    private final String subject;
    private final String body;
    private String status;
    private String errorMessage;

    public EmailStatus emailSuccess() {
        this.status = ApplicationConstant.EMAIL_SUCCESS;
        return this;
    }

    public EmailStatus emailFailure() {
        this.status = ApplicationConstant.EMAIL_FAIL;
        return this;
    }


}