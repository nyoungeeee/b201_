package com.nyoung.b201.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "사용자 생성 및 수정 요청")
public class UserRequest {

    private Long userId;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자여야 합니다")
    @Schema(
            description = "비밀번호",
            example = "P@ssw0rd!",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @NotBlank(message = "이메일은 필수입니다")
    @Schema(
            description = "이메일",
            example = "test@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank(message = "닉네임은/이름은 필수입니다")
    @Schema(
            description = "닉네임/이름",
            example = "루큐츄",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    private String team;

    @NotBlank(message = "가입경로는 필수입니다")
    @Schema(
            description = "가입경로",
            example = "kakao/gmail/naver",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String provider;
}
