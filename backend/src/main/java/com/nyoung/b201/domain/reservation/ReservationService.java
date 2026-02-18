package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.dto.ReservationCreateRequest;
import com.nyoung.b201.domain.reservation.dto.ReservationCreateResponse;
import com.nyoung.b201.domain.reservation.dto.ReservationScheduleResponse;
import com.nyoung.b201.domain.reservation.exception.ReservationRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRequestRepository requestRepository;
    private final ReservationSlotRepository slotRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Transactional
    public ReservationCreateResponse create(ReservationCreateRequest request, Long currentUserId) {
        ReservationRequestValidator.validateCreateRequest(request);
        // TODO:
        // 1) currentUser role 체크 (GUEST면 예외)
        // 2) ReservationRequest(PENDING) 저장
        // 3) WEEKLY면 날짜 리스트 생성, timeSlot 매핑 리스트 계산
        // 4) slots preview 반환 (실제 slot 생성은 승인 시점에)
        return ReservationCreateResponse.builder()
                .reservationRequestId(0L)
                .status(ReservationCreateResponse.ReservationRequestStatus.PENDING)
                .generatedSlotCount(0)
                .requestedAt(java.time.LocalDateTime.now())
                .build();
    }

    @Transactional(readOnly = true)
    public ReservationScheduleResponse getSchedule(LocalDate from, LocalDate to, Long viewerUserId) {
        // TODO: slotRepository.findByDateRange(from, to) + DTO 매핑
        return ReservationScheduleResponse.builder().days(java.util.List.of()).build();
    }

    @Transactional
    public void cancelRequest(Long reservationRequestId, Long currentUserId) {
        // TODO: PENDING만 취소 허용 등 정책 확정 후 구현
    }
}
