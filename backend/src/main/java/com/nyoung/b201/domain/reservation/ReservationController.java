package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.dto.ReservationCreateRequest;
import com.nyoung.b201.domain.reservation.dto.ReservationCreateResponse;
import com.nyoung.b201.domain.reservation.dto.ReservationScheduleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    // TODO: ReservationService 주입
    // private final ReservationService reservationService;
    private final ReservationService reservationService;
    private final ReservationAdminService reservationAdminService;

    /**
     * 예약 신청 (개인/팀, 단건/반복)
     * - globalRole이 GUEST면 403(서비스/시큐리티에서 처리 권장)
     */
    @PostMapping
    public ReservationCreateResponse create(@Valid @RequestBody ReservationCreateRequest request) {
        // TODO: 현재 로그인 유저 식별 (SecurityContext)
        // return reservationService.create(request, currentUserId);
        return ReservationCreateResponse.builder()
                .reservationRequestId(0L)
                .status(ReservationCreateResponse.ReservationRequestStatus.PENDING)
                .generatedSlotCount(0)
                .requestedAt(java.time.LocalDateTime.now())
                .build();
    }

    /**
     * 스케줄 조회 (GUEST도 가능)
     * - from/to는 inclusive로 볼지, to-exclusive로 볼지 확정해서 문서에 고정 추천
     */
    @GetMapping("/schedule")
    public ReservationScheduleResponse schedule(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        // TODO: return reservationService.getSchedule(from, to, viewerUserIdOrNull);
        return ReservationScheduleResponse.builder().days(java.util.List.of()).build();
    }

    /**
     * 내 예약 신청 내역 조회 (선택)
     * - 개인 예약 신청, 팀 예약 신청(리더/멤버 가시성 정책)에 따라 분기 가능
     */
    @GetMapping("/me/requests")
    public Object myRequests() {
        // TODO: return reservationService.getMyRequests(currentUserId);
        return java.util.Map.of("items", java.util.List.of());
    }

    /**
     * 예약 신청 취소 (선택)
     * - 정책: PENDING만 취소 가능? APPROVED도 취소 가능?
     */
    @PostMapping("/{reservationRequestId}/cancel")
    public Object cancel(@PathVariable Long reservationRequestId) {
        // TODO: reservationService.cancelRequest(reservationRequestId, currentUserId);
        return java.util.Map.of("ok", true);
    }
}