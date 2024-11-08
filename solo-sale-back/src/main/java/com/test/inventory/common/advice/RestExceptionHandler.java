package com.test.inventory.common.advice;

import com.test.inventory.common.exception.ApiError;
import com.test.inventory.common.exception.CommonServerException;
import com.test.inventory.common.exception.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        logger.error("DataIntegrityViolationException: {}", ex.getCause().getMessage().split("\\[")[1].replaceAll("]", ""));
        return new ResponseEntity<>("Database denied the operation. Contact support.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommonServerException.class)
    public final ResponseEntity<Object> handleServerException(CommonServerException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }


    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<?> handleCredentialException(
            Exception ex, WebRequest request
    ) {
        ApiError apiError = new ApiError();
        apiError.addError(new ErrorDto("server101", "Invalid User Name or Password"));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException(
            Exception ex, WebRequest request
    ) {
        ApiError apiError = new ApiError();
        apiError.addError(new ErrorDto("server102", "Invalid User Name"));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(HttpStatus.BAD_REQUEST.name(), msg));

    }

}