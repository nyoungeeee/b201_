package com.nyoung.b201.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "성공 시 응답 포맷")
@Getter
@AllArgsConstructor
public class SuccessResponse<T> {

    @Schema(description = "결과 코드", example = "SUCCESS")
    private String code;

    @Schema(description = "응답 메시지", example = "요청이 성공했습니다")
    private String message;

    @Schema(description = "응답 데이터")
    private T data;

    public static <T> SuccessResponse<T> success(T data) {
        return new SuccessResponse<>("SUCCESS", "요청이 성공했습니다", data);
    }

    public static <T> SuccessResponse<T> success(T data, String message) {
        return new SuccessResponse<>("SUCCESS", message, data);
    }

    public static SuccessResponse<Void> success(String message) {
        return new SuccessResponse<>("SUCCESS", message, null);
    }

    public static SuccessResponse<Void> success() {
        return new SuccessResponse<>("SUCCESS", "요청이 성공했습니다", null);
    }

}
