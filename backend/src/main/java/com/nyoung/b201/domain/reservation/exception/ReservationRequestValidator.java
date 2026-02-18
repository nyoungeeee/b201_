package com.nyoung.b201.domain.reservation.exception;

import com.nyoung.b201.domain.reservation.dto.ReservationCreateRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public final class ReservationRequestValidator {

    private static final long MAX_PERIOD_DAYS = 92; // 약 3개월

    private ReservationRequestValidator() {}

    public static void validateCreateRequest(ReservationCreateRequest req) {
        if (req.getRequesterType() == null) throw new ReservationPolicyViolationException("requesterType 필수");
        if (req.getRepeatType() == null) throw new ReservationPolicyViolationException("repeatType 필수");

        if (req.getRequesterType() == ReservationCreateRequest.RequesterType.TEAM && req.getTeamId() == null) {
            throw new ReservationPolicyViolationException("팀 예약은 teamId 필수");
        }

        LocalTime start = req.getStartTime();
        LocalTime end = req.getEndTime();
        if (start == null || end == null) throw new ReservationPolicyViolationException("startTime/endTime 필수");
        if (!start.isBefore(end)) throw new ReservationPolicyViolationException("startTime < endTime 이어야 함");
        if (!is30MinAligned(start) || !is30MinAligned(end)) {
            throw new ReservationPolicyViolationException("시간은 30분 단위만 가능");
        }

        if (req.getRepeatType() == ReservationCreateRequest.ReservationRepeatType.ONCE) {
            if (req.getDate() == null) throw new ReservationPolicyViolationException("단건 예약은 date 필수");
        } else {
            if (req.getStartDate() == null || req.getEndDate() == null)
                throw new ReservationPolicyViolationException("반복 예약은 startDate/endDate 필수");
            if (req.getDaysOfWeek() == null || req.getDaysOfWeek().isEmpty())
                throw new ReservationPolicyViolationException("반복 예약은 daysOfWeek 필수");

            LocalDate s = req.getStartDate();
            LocalDate e = req.getEndDate();
            if (e.isBefore(s)) throw new ReservationPolicyViolationException("endDate는 startDate 이전 불가");

            long days = ChronoUnit.DAYS.between(s, e) + 1;
            if (days > MAX_PERIOD_DAYS) throw new ReservationPolicyViolationException("반복 예약 기간은 최대 3개월");
        }
    }

    private static boolean is30MinAligned(LocalTime t) {
        return t.getSecond() == 0 && t.getNano() == 0 && (t.getMinute() == 0 || t.getMinute() == 30);
    }
}