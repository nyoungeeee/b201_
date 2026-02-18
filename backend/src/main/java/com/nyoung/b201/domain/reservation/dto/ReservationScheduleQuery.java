package com.nyoung.b201.domain.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class ReservationScheduleQuery {

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to; // inclusive/exclusive는 컨트롤러에서 문서로 명시
}