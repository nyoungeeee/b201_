package com.nyoung.b201.common;

import com.nyoung.b201.domain.user.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException e) {

        ErrorCode errorCode = ErrorCode.USER_NOT_FOUND;

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        ErrorCode.USER_NOT_FOUND,
                        e.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e
    ) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        Map<String, String> fieldErrors = new HashMap<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(new ErrorResponse(
                        ErrorCode.VALIDATION_ERROR,
                        errorCode.getMessage(),
                        fieldErrors
                ));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKey(
            DataIntegrityViolationException e) {

        return ResponseEntity
                .status(ErrorCode.DUPLICATE_EMAIL.getHttpStatus())
                .body(new ErrorResponse(
                        ErrorCode.DUPLICATE_EMAIL,
                        ErrorCode.DUPLICATE_EMAIL.getMessage()
                ));
    }
}
