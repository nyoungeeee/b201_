package com.nyoung.b201.domain.reservation.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDecisionRequest {

    @Size(max = 500)
    private String reason; // 반려 사유 또는 승인 메모
}
