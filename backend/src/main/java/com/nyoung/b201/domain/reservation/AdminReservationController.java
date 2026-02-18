package com.nyoung.b201.domain.reservation;


import com.nyoung.b201.domain.reservation.dto.AdminDecisionRequest;
import com.nyoung.b201.domain.reservation.dto.PendingReservationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/reservations")
@RequiredArgsConstructor
public class AdminReservationController {

    // TODO: ReservationAdminService 주입
    // private final ReservationAdminService reservationAdminService;

    /**
     * 승인 대기 목록
     * - ADMIN만 접근
     */
    @GetMapping("/pending")
    public PendingReservationResponse pending() {
        // TODO: return reservationAdminService.getPendingList();
        return PendingReservationResponse.builder().items(java.util.List.of()).build();
    }

    /**
     * 승인
     * - 승인 시점 선착순: 승인 로직에서 슬롯 충돌 검사 후 처리
     */
    @PostMapping("/{reservationRequestId}/approve")
    public Object approve(
            @PathVariable Long reservationRequestId,
            @Valid @RequestBody(required = false) AdminDecisionRequest request
    ) {
        // TODO: reservationAdminService.approve(reservationRequestId, request, adminUserId);
        return java.util.Map.of("ok", true);
    }

    /**
     * 반려
     */
    @PostMapping("/{reservationRequestId}/reject")
    public Object reject(
            @PathVariable Long reservationRequestId,
            @Valid @RequestBody(required = false) AdminDecisionRequest request
    ) {
        // TODO: reservationAdminService.reject(reservationRequestId, request, adminUserId);
        return java.util.Map.of("ok", true);
    }
}