package com.test.inventory.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Setter
@Getter
public class CommonServerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1436995162658277359L;
    private final String msg;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CommonServerException(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }

    public static CommonServerException badRequest(String msg) {
        return new CommonServerException(msg, HttpStatus.BAD_REQUEST);
    }

    public static CommonServerException notFound(String msg) {
        return new CommonServerException(msg, HttpStatus.NOT_FOUND);
    }

    public static CommonServerException dataSaveException(String msg) {
        return new CommonServerException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static CommonServerException internalServerException(String msg) {
        return new CommonServerException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static CommonServerException methodNotAllowed(String msg) {
        return new CommonServerException(msg, HttpStatus.UNAUTHORIZED);
    }

    public static CommonServerException emailSendingException(String msg) {
        return new CommonServerException(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static CommonServerException notAuthorized(String msg) {
        return new CommonServerException(msg, HttpStatus.FORBIDDEN
        );
    }
}