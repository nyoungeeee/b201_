package com.nyoung.b201.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PendingReservationResponse {

    private List<Item> items;

    @Getter
    @AllArgsConstructor
    public static class Item {
        private Long reservationRequestId;
        private String requesterDisplay; // 팀명 또는 닉네임
        private RequesterType requesterType;

        private String repeatType; // ONCE / WEEKLY
        private String startDate;  // nullable
        private String endDate;    // nullable

        private String startTime;
        private String endTime;

        private LocalDateTime requestedAt;

        // (선택) 슬롯 프리뷰
        private int slotCount;
    }

    public enum RequesterType {
        USER, TEAM
    }
}