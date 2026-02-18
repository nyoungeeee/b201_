package com.nyoung.b201.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ReservationCreateResponse {
    private Long reservationRequestId;         // 신청서 ID
    private ReservationRequestStatus status;   // PENDING 고정 예상
    private int generatedSlotCount;            // 생성될(또는 생성한) 슬롯 수
    private LocalDateTime requestedAt;

    // (선택) 단건이면 1개, 반복이면 여러 날짜/시간 미리보기
    private List<SlotPreview> slots;

    @Getter
    @AllArgsConstructor
    public static class SlotPreview {
        private String date;      // ISO-8601 "2026-02-19"
        private String startTime; // "19:00"
        private String endTime;   // "21:00"
    }

    public enum ReservationRequestStatus {
        PENDING, APPROVED, REJECTED, CANCELED
    }
}