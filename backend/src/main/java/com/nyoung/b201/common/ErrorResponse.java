package com.nyoung.b201.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Map;

@Schema(description = "에러 발생 시 응답 포맷")
@Getter
public class ErrorResponse {

    private ErrorCode code;
    private String message;

    @Schema(description = "필드별 에러 메시지", nullable = true)
    private final Map<String, String> errors;

    public ErrorResponse(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
        this.errors = null;
    }

    public ErrorResponse(ErrorCode code, String message, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}