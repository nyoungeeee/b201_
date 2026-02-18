package com.nyoung.b201.domain.user;

import com.nyoung.b201.common.ErrorResponse;
import com.nyoung.b201.common.SuccessResponse;
import com.nyoung.b201.domain.user.dto.UserRequest;
import com.nyoung.b201.domain.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "수정 성공",
                content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation 실패",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "사용자 없음",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "전체 사용자 조회")
    public ResponseEntity<SuccessResponse<List<UserResponse>>> findAll() {
        List<UserResponse> users = userService.findAll();
        return ResponseEntity.ok(SuccessResponse.success(users));
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회")
    public ResponseEntity<SuccessResponse<UserResponse>> find(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(SuccessResponse.success(userService.find(userId)));
    }

    @PostMapping
    @Operation(summary = "사용자 생성", description = "신규 사용자를 생성합니다")
    public ResponseEntity<SuccessResponse<UserResponse>> create(@Valid @RequestBody UserRequest req) {
        return ResponseEntity.ok(SuccessResponse.success(
                userService.create(req), "사용자가 생성되었습니다.")
        );
    }

    @PutMapping
    @Operation(summary = "사용자 정보 수정", description = "사용자 정보를 수정합니다")
    public ResponseEntity<SuccessResponse<Void>> update(@Valid UserRequest req) {
        userService.update(req);
        return ResponseEntity.ok(SuccessResponse.success());
    }

//    @DeleteMapping
//    @Operation(summary = "사용자 삭제")
//    public ResponseEntity<SuccessResponse<Void>> delete(Long id) {
//        userService.delete(id);
//        return ResponseEntity.ok(SuccessResponse.success());
//    }

//    @GetMapping("/admincheck/{id}")
//    @Operation(summary = "운영자 여부 체크", description = "해당 계정의 운영자 여부를 체크합니다.")
//    public ResponseEntity<SuccessResponse<Boolean>> check(@PathVariable("id") Long userId) {
//        return ResponseEntity.ok(SuccessResponse.success(userService.checkAdmin(userId)));
//    }
}
