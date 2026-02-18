package com.nyoung.b201.domain.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ReservationScheduleResponse {

    // 날짜별 예약 슬롯 목록
    private List<DaySchedule> days;

    @Getter
    @AllArgsConstructor
    public static class DaySchedule {
        private String date; // "2026-02-19"
        private List<Slot> slots;
    }

    @Getter
    @AllArgsConstructor
    public static class Slot {
        private String startTime; // "19:00"
        private String endTime;   // "19:30"
        private SlotStatus status; // APPROVED만 보여줄지, PENDING도 보여줄지 정책에 따라
        private SlotOwner owner;   // (선택) 팀명/닉네임 노출 정책에 따라 마스킹 가능
    }

    public enum SlotStatus {
        APPROVED,
        PENDING
    }

    @Getter
    @AllArgsConstructor
    public static class SlotOwner {
        private OwnerType type; // USER/TEAM
        private String displayName; // 팀명 또는 닉네임(정책에 따라 "예약됨" 등으로 마스킹 가능)
    }

    public enum OwnerType {
        USER, TEAM
    }
}
