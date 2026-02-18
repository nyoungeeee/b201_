package com.nyoung.b201.domain.reservation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationCreateRequest {

    @NotNull
    private RequesterType requesterType; // USER or TEAM

    // requesterType == TEAM 일 때만 사용
    private Long teamId;

    @NotNull
    private ReservationRepeatType repeatType; // ONCE or WEEKLY

    // repeatType == ONCE 일 때만 사용 (단건 날짜)
    private LocalDate date;

    // repeatType == WEEKLY 일 때만 사용 (기간 + 요일)
    private LocalDate startDate;

    private LocalDate endDate; // 최대 3개월(서비스에서 검증 권장)

    private Set<DayOfWeekK> daysOfWeek; // WEEKLY일 때 필수

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    // 선택: 신청 사유 등
    @Size(max = 500)
    private String note;

    public enum RequesterType {
        USER, TEAM
    }

    public enum ReservationRepeatType {
        ONCE, WEEKLY
    }

    // Java의 DayOfWeek 대신, 직렬화/가독성 위해 명시 (원하면 java.time.DayOfWeek 사용해도 됨)
    public enum DayOfWeekK {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }
}