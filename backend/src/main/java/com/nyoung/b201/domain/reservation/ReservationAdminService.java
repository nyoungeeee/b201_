package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.dto.AdminDecisionRequest;
import com.nyoung.b201.domain.reservation.dto.PendingReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationAdminService {

    private final ReservationRequestRepository requestRepository;
    private final ReservationSlotRepository slotRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final ApprovalRepository approvalRepository;

    @Transactional(readOnly = true)
    public PendingReservationResponse getPendingList() {
        // TODO: requestRepository.findByStatusOrderByCreatedAtAsc(PENDING) -> DTO
        return PendingReservationResponse.builder().items(java.util.List.of()).build();
    }

    @Transactional
    public void approve(Long reservationRequestId, AdminDecisionRequest req, Long adminUserId) {
        // TODO:
        // 1) 요청서(PENDING) 조회
        // 2) 승인하려는 모든 날짜/timeSlotIds 계산
        // 3) 충돌 검사(slotRepository.findConflicts)
        // 4) conflict 없으면 ReservationSlot insert (유니크키로 동시성 최종 방어)
        // 5) request APPROVED + Approval 기록
        // 6) 알림 생성(승인)
    }

    @Transactional
    public void reject(Long reservationRequestId, AdminDecisionRequest req, Long adminUserId) {
        // TODO:
        // 1) 요청서(PENDING) 조회
        // 2) request REJECTED + Approval 기록
        // 3) 알림 생성(반려)
    }
}
