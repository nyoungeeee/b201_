package com.nyoung.b201.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "User not found"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "USER_002", "이미 사용 중인 이메일입니다"),

    // AUTH
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_001", "Invalid password"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH_002", "Access denied"),

    // COMMON
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_001", "Invalid request"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "COMMON_002", "Validation error");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}
